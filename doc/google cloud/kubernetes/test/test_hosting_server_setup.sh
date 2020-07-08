#!/bin/sh

sudo su -

sudo nano /etc/rc.local

iptables -P INPUT ACCEPT
iptables -F
iptables -A INPUT -i lo -j ACCEPT
iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
iptables -A INPUT -p tcp --dport 22 -j ACCEPT
iptables -A INPUT -p tcp --dport 8080 -j ACCEPT
iptables -A INPUT -p tcp --dport 8443 -j ACCEPT
iptables -A INPUT -p tcp --dport 17505 -j ACCEPT
iptables -A INPUT -p tcp --dport 17508 -j ACCEPT
iptables -A INPUT -p tcp --dport 17510 -j ACCEPT
iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-ports 8080
iptables -t nat -A PREROUTING -p tcp --dport 443 -j REDIRECT --to-ports 8443
iptables -t nat -I OUTPUT -p tcp -d 127.0.0.1 --dport 80 -j REDIRECT --to-ports 8080
iptables -t nat -I OUTPUT -p tcp -d 127.0.0.1 --dport 443 -j REDIRECT --to-ports 8443
iptables -P INPUT DROP
iptables -P FORWARD DROP
iptables -P OUTPUT ACCEPT
iptables -L -v
/etc/rc.local # Reinitialize iptables rules

# CONFIGURE MYSQL SERVER

apt-get install mysql-server
mysql -p -u root
- create database sym_server;
- grant all on sym_server.* to 'sym_sys'@'localhost' identified by 'sysP@$$WORDADM1N';
# REVOKE ALL ON sym_server.* FROM 'sym_sys'@'localhost';
#nano /etc/mysql/my.cnf
#- wait_timeout=259200
service mysql restart

# CONFIGURE MAIL SERVER RELAY

/etc/init.d/postfix restart

# START JENKINS

service jenkins start

# CONFIGURE WILDFLY
cp -r /home/tkaviya/mysql/ /opt/wildfly/modules/system/layers/base/com/
chown -R wildfly:jenkins /opt/wildfly/modules/system/layers/base/com/mysql/
chmod -R g+w /opt/wildfly/modules/system/layers/base/com/


#CONFIGURE ANDROID
mkdir -p /opt/android-sdk/
cd /opt/android-sdk/
wget https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip
unzip sdk-tools-linux-4333796.zip
cd /opt/android-sdk/
tools/bin/sdkmanager "platform-tools" "platforms;android-29" "build-tools;29.0.0"

nano /etc/environment
-ANDROID_HOME=/opt/android-sdk/
export ANDROID_HOME=/opt/android-sdk/
service jenkins restart

wget https://services.gradle.org/distributions/gradle-5.5-bin.zip
unzip gradle-5.5-bin.zip
rm /usr/bin/gradle
ln -s /opt/gradle-5.5/bin/gradle /usr/bin/gradle

nano /etc/profile
-PATH="$JAVA_HOME/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/opt/android-sdk/:/opt/android-sdk/tools/bin:/opt/android-sdk/platform-tools"
export PATH="$PATH:/opt/android-sdk/:/opt/android-sdk/tools/bin:/opt/android-sdk/platform-tools"

keytool -genkey -v -keystore /root/application.keystore -alias ettl_vision -keyalg RSA -keysize 2048 -validity 10000