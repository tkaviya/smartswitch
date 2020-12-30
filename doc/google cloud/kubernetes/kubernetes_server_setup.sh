#!/bin/sh

sudo su -

apt-get upgrade -y

chown -R tkaviya:tkaviya /home/tkaviya
chown -R pmutyambizi:pmutyambizi /home/pmutyambizi

chown root:syslog /var/log
chmod ug+wrx /var/log

chown jenkins:jenkins /var/lib/jenkins
	
# INSTALL GRADLE
wget https://services.gradle.org/distributions/gradle-6.3-bin.zip -P /root/installers/
unzip -d /opt/gradle /root/installers/gradle-*.zip
echo 'GRADLE_HOME=/opt/gradle/gradle-6.3' > /etc/environment
echo "PATH=${PATH}:${GRADLE_HOME}/bin" >> /etc/environment
ln -s /opt/gradle/gradle-6.3/bin/gradle /usr/bin/gradle
source /etc/environment

npm install -g @ionic/cli
npm install -g cordova@9.0.0

#timedatectl set-timezone Africa/Accra
updatedb

# CONFIGURE SSH

# su tkaviya
# ssh-keygen -t rsa -f ~/.ssh/my-ssh-key -C tkaviya
# exit

# su pmutyambizi
# ssh-keygen -t rsa -f ~/.ssh/my-ssh-key -C pmutyambizi
# exit

# SETUP POSTFIX
apt-get install -y postfix
cp /etc/postfix/main.cf.proto /etc/postfix/main.cf
postconf -e 'smtpd_sasl_auth_enable = yes'
postconf -e 'smtp_sasl_auth_enable = yes'
postconf -e 'smtp_sasl_password_maps = hash:/etc/postfix/sasl_passwd'
postconf -e 'smtp_sasl_security_options = noanonymous'
postconf -e 'smtp_sasl_tls_security_options = noanonymous'
postconf -e 'smtp_tls_security_level = encrypt'
postconf -e 'header_size_limit = 4096000'
postconf -e 'relayhost = [smtp.sendgrid.net]:2525'
postconf -e 'setgid_group = postdrop'
postconf -e 'sendmail_path = /usr/sbin/sendmail'
postconf -e 'mynetworks = 10.128.0.0/20, 10.4.0.0/20, 10.8.0.0/20, 127.0.0.0/8'
sed -i 's/mailq_path =/#mailq_path =/g' /etc/postfix/main.cf
sed -i 's/manpage_directory =/#manpage_directory =/g' /etc/postfix/main.cf
sed -i 's/html_directory =/#html_directory =/g' /etc/postfix/main.cf
sed -i 's/sample_directory =/#sample_directory =/g' /etc/postfix/main.cf
sed -i 's/newaliases_path =/#newaliases_path =/g' /etc/postfix/main.cf
sed -i 's/readme_directory =/#readme_directory =/g' /etc/postfix/main.cf
echo '[smtp.sendgrid.net]:2525 t3ratech:3mp0wER@dm1n' > /etc/postfix/sasl_passwd
postmap /etc/postfix/sasl_passwd
rm -rf /etc/postfix/sasl_passwd
chmod 600 /etc/postfix/sasl_passwd.db
systemctl enable postfix
rm -rf /var/spool/postfix/pid
service postfix restart

# SETUP MYSQL
useradd -m mysql
chown -R mysql:mysql /var/lib/mysql
#sudo apt purge -y mysql*
# rm -rf /var/lib/mysql/*
# rm -rf /etc/mysql/*
#apt-get autoremove -y
apt-get install -y mysql-server
#systemctl enable mysql
#usermod -d /var/lib/mysql/ mysql
service mysql restart
mysql_secure_installation
mysql -p -u root
- create database sym_server;
- CREATE USER 'sym_sys'@'localhost' IDENTIFIED BY 'sysP@$$WORDADM1N';
- GRANT ALL PRIVILEGES ON sym_server.* TO 'sym_sys'@'localhost' WITH GRANT OPTION;
echo 'wait_timeout=25920000' >> /etc/mysql/mysql.conf.d/mysqld.cnf
service mysql restart

# WILDFLY SETUP
useradd -m wildfly
usermod -a -G wildfly jenkins
cd /root/installers/
curl -O https://download.jboss.org/wildfly/19.0.0.Final/wildfly-19.0.0.Final.tar.gz
sha1sum wildfly-19.0.0.Final.tar.gz | grep 0d47c0e8054353f3e2749c11214eab5bc7d78a14
tar xf wildfly-19.0.0.Final.tar.gz -C  /opt/
chown -R wildfly:wildfly /opt/wildfly*
ln -s /opt/wildfly-19.0.0.Final /opt/wildfly

#curl -O https://download.jboss.org/wildfly/17.0.1.Final/wildfly-17.0.1.Final.tar.gz
#sha1sum wildfly-17.0.1.Final.tar.gz | grep eaef7a87062837c215e54511c4ada8951f0bd8d5
#tar xf wildfly-17.0.1.Final.tar.gz -C /opt/
#ln -s /opt/wildfly-17.0.1.Final /opt/wildfly

chown -R wildfly:wildfly /opt/wildfly
chmod -R g+rw /opt/wildfly
mkdir -p /etc/wildfly
cp /opt/wildfly/docs/contrib/scripts/systemd/wildfly.conf /etc/wildfly/
cp /opt/wildfly/docs/contrib/scripts/systemd/wildfly.conf /etc/default/wildfly
cp /opt/wildfly/docs/contrib/scripts/systemd/launch.sh /opt/wildfly/bin/
chmod +x /opt/wildfly/bin/*.sh
cp /opt/wildfly/docs/contrib/scripts/systemd/wildfly.service /etc/systemd/system/
cp /opt/wildfly/docs/contrib/scripts/init.d/wildfly-init-debian.sh /etc/init.d/wildfly
sed -i 's/jboss.bind.address:127.0.0.1/jboss.bind.address:0.0.0.0/g' /opt/wildfly/standalone/configuration/standalone.xml
sed -i -e 's,<socket-binding name="http" port="${jboss.http.port:80}"/>,<socket-binding name="http" port="${jboss.http.port:8080}"/>,g' /opt/wildfly/standalone/configuration/standalone.xml
sed -i -e 's,<socket-binding name="https" port="${jboss.https.port:443}"/>,<socket-binding name="https" port="${jboss.https.port:8443}"/>,g' /opt/wildfly/standalone/configuration/standalone.xml
cp -R /home/tkaviya/com/ /opt/wildfly/modules/system/layers/base/
cp -R /home/tkaviya/org/ /opt/wildfly/modules/system/layers/base/
chmod -R ug+wr /opt/wildfly/standalone/deployments/
cp /home/tkaviya/t3ratech.jks /opt/wildfly/standalone/configuration/
mkdir -p /opt/integration/mtn/

# add jenkins to wildfly group for deployment
usermod -a -G wildfly jenkins
chown -R wildfly:wildfly /opt/wildfly/
chown -R wildfly:jenkins /opt/wildfly/standalone/deployments/
chown -R root:wildfly /opt/integration/mtn/

systemctl enable wildfly
service wildfly restart &
tail -f /opt/wildfly/standalone/log/server.log



# CONFIGURE iptables
apt-get install -y iptables
echo 'iptables -P INPUT ACCEPT' >> /etc/rc.local
echo 'iptables -F' >> /etc/rc.local
echo 'iptables -A INPUT -i lo -j ACCEPT' >> /etc/rc.local
echo 'iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT' >> /etc/rc.local
echo 'iptables -A INPUT -p tcp --dport 22 -j ACCEPT' >> /etc/rc.local
echo 'iptables -A INPUT -p tcp --dport 8008 -j ACCEPT' >> /etc/rc.local
echo 'iptables -A INPUT -p tcp --dport 8080 -j ACCEPT' >> /etc/rc.local
echo 'iptables -A INPUT -p tcp --dport 8443 -j ACCEPT' >> /etc/rc.local
echo 'iptables -A INPUT -p tcp --dport 17505 -j ACCEPT' >> /etc/rc.local
echo 'iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-ports 8080' >> /etc/rc.local
echo 'iptables -t nat -A PREROUTING -p tcp --dport 443 -j REDIRECT --to-ports 8443' >> /etc/rc.local
echo 'iptables -t nat -I OUTPUT -p tcp -d 127.0.0.1 --dport 80 -j REDIRECT --to-ports 8080' >> /etc/rc.local
echo 'iptables -t nat -I OUTPUT -p tcp -d 127.0.0.1 --dport 443 -j REDIRECT --to-ports 8443' >> /etc/rc.local
echo 'iptables -P INPUT DROP' >> /etc/rc.local
echo 'iptables -P FORWARD DROP' >> /etc/rc.local
echo 'iptables -P OUTPUT ACCEPT' >> /etc/rc.local
sh /etc/rc.local # Reinitialize iptables rules
iptables -L -v

# SETUP APACHE SERVER
apt-get install -y apache2

# configure apache server
sed -i 's/KeepAlive On/KeepAlive Off/g' /etc/apache2/apache2.conf
echo "TraceEnable off" >> /etc/apache2/apache2.conf
#nano /etc/httpd/conf/sites-available/default-ssl.conf
#-ServerAdmin admin@t3ratech.com
#nano /etc/apache2/sites-available/000-default.conf
#-ServerAdmin admin@t3ratech.com
mkdir -p /var/www/html/symbiosis
chown -R jenkins:jenkins /var/www/html/symbiosis
#a2dismod mpm_event
#a2enmod mpm_prefork

# configure ports
sed -i 's/Listen 80/Listen 8008/g' /etc/apache2/ports.conf

# configure server hardening
sed -i 's/ServerSignature On/ServerSignature Off/g' /etc/apache2/conf-enabled/security.conf
sed -i 's/ServerTokens OS/ServerTokens Minimal/g' /etc/apache2/conf-enabled/security.conf

apt install libapache2-mod-evasive -y

systemctl enable apache2
service apache2 restart


# CREATE SSL KEYSTORE

#CONFIGURE PGP
chown wildfly:wildfly /opt/wildfly/modules/system/layers/base/org/bouncycastle/main/*
service wildfly restart && tail -f /opt/wildfly/standalone/log/server.log

#CONFIGURE ANDROID
mkdir -p /opt/android-sdk/
cd /root/installers/
wget https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip
unzip -d /opt/android-sdk/ sdk-tools-linux-4333796.zip
alternatives --config java
- 2
cd /opt/android-sdk/
tools/bin/sdkmanager "platform-tools" "platforms;android-29" "build-tools;29.0.0"
tools/bin/sdkmanager "platform-tools" "platforms;android-28" "build-tools;28.0.0"
tools/bin/sdkmanager "platform-tools" "platforms;android-19" "build-tools;19.0.0"

echo "ANDROID_HOME=/opt/android-sdk/" >> /etc/environment
echo "ANDROID_SDK_ROOT=/opt/android-sdk/" >> /etc/environment
export ANDROID_HOME=/opt/android-sdk/
export ANDROID_SDK_ROOT=/opt/android-sdk/
service jenkins restart

nano /etc/profile
-PATH="$JAVA_HOME/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/opt/android-sdk/:/opt/android-sdk/tools/bin:/opt/android-sdk/platform-tools"
export PATH="$PATH:/opt/android-sdk/:/opt/android-sdk/tools/bin:/opt/android-sdk/platform-tools"

keytool -genkey -v -keystore /root/certificates/application.keystore -alias sym_mobile -keyalg RSA -keysize 2048 -validity 10000