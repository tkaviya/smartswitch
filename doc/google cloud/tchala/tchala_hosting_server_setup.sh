#!/bin/sh

sudo su -

# CREATE USER ACCOUNT

useradd -m tkaviya
useradd -m pmutyambizi

passwd tkaviya
-XXXXX

passwd pmutyambizi
-annatis

usermod -a -G sudo pmutyambizi


# INSTALL GENERAL SERVER PROGRAMS

dnf install -y bind-utils vsftpd mlocate nmap telnet bzip2 wget mlocate nmap telnet links unzip man git dbus systemd nano mysql-server npm java-11-openjdk-devel java-1.8.0-openjdk-devel postfix redhat-lsb nodejs-devel cyrus-sasl-plain

yum distro-sync -y

#wget https://services.gradle.org/distributions/gradle-5.0-bin.zip -P /tmp
#unzip -d /opt/gradle /tmp/gradle-*.zip
#echo 'export GRADLE_HOME=/opt/gradle/gradle-5.0' > /etc/profile.d/gradle.sh
#echo 'export PATH=${GRADLE_HOME}/bin:${PATH}' >> /etc/profile.d/gradle.sh
#ln -s /opt/gradle-5.0/bin/gradle /usr/bin/gradle
#chmod +x /etc/profile.d/gradle.sh
#source /etc/profile.d/gradle.sh


wget https://services.gradle.org/distributions/gradle-6.3-bin.zip -P /tmp
unzip -d /opt/gradle /tmp/gradle-*.zip
echo 'export GRADLE_HOME=/opt/gradle/gradle-6.3' > /etc/profile.d/gradle.sh
echo 'export PATH=${GRADLE_HOME}/bin:${PATH}' >> /etc/profile.d/gradle.sh
ln -s /opt/gradle-6.3/bin/gradle /usr/bin/gradle
chmod +x /etc/profile.d/gradle.sh
source /etc/profile.d/gradle.sh

npm install -g @ionic/cli
npm install -g cordova@9.0.0

alternatives --config java

timedatectl set-timezone Africa/Accra
updatedb

# CONFIGURE SSH

su tkaviya
ssh-keygen -t rsa -f ~/.ssh/my-ssh-key -C tkaviya
exit

su pmutyambizi
ssh-keygen -t rsa -f ~/.ssh/my-ssh-key -C pmutyambizi
exit

sed -ri 's/PasswordAuthentication yes/PasswordAuthentication no/g' /etc/ssh/sshd_config
echo "KexAlgorithms curve25519-sha256,curve25519-sha256@libssh.org,diffie-hellman-group16-sha512,diffie-hellman-group18-sha512,diffie-hellman-group-exchange-sha256" >> /etc/ssh/sshd_config
echo "Ciphers chacha20-poly1305@openssh.com,aes256-gcm@openssh.com,aes128-gcm@openssh.com,aes256-ctr,aes192-ctr,aes128-ctr" >> /etc/ssh/sshd_config
echo "MACs hmac-sha2-256-etm@openssh.com,hmac-sha2-512-etm@openssh.com,umac-128-etm@openssh.com" >> /etc/ssh/sshd_config
echo "HostKeyAlgorithms ssh-ed25519,ssh-ed25519-cert-v01@openssh.com" >> /etc/ssh/sshd_config
service sshd restart

# FTP SERVER CONFIG

#sed -i 's/#chroot_local_user=YES/chroot_local_user=YES/g' /etc/vsftpd/vsftpd.conf
#echo "pasv_enable=YES" >> /etc/vsftpd/vsftpd.conf
#echo "pasv_addr_resolve=YES" >> /etc/vsftpd/vsftpd.conf
#echo "pasv_min_port=6000" >> /etc/vsftpd/vsftpd.conf
#echo "pasv_max_port=6010" >> /etc/vsftpd/vsftpd.conf
#systemctl enable vsftpd
#service vsftpd restart
#
##-connect_from_port_20=YES
##-anonymous_enable=NO
##-local_enable=YES
##-pasv_address=104.154.237.6

# SETUP POSTFIX
cp /etc/postfix/main.cf.proto /etc/postfix/main.cf
postconf -e 'smtp_sasl_auth_enable = yes'
postconf -e 'smtp_sasl_password_maps = hash:/etc/postfix/sasl_passwd'
postconf -e 'smtp_sasl_security_options = noanonymous'
postconf -e 'smtp_sasl_tls_security_options = noanonymous'
postconf -e 'smtp_tls_security_level = encrypt'
postconf -e 'header_size_limit = 4096000'
postconf -e 'relayhost = [smtp.sendgrid.net]:2525'
postconf -e 'setgid_group = postdrop'
postconf -e 'mynetworks = 10.128.0.0/20, 127.0.0.0/8'
sed -i 's/sendmail_path =/#sendmail_path =/g' /etc/postfix/main.cf
sed -i 's/mailq_path =/#mailq_path =/g' /etc/postfix/main.cf
sed -i 's/manpage_directory =/#manpage_directory =/g' /etc/postfix/main.cf
sed -i 's/html_directory =/#html_directory =/g' /etc/postfix/main.cf
sed -i 's/sample_directory =/#sample_directory =/g' /etc/postfix/main.cf
sed -i 's/newaliases_path =/#newaliases_path =/g' /etc/postfix/main.cf
sed -i 's/readme_directory =/#readme_directory =/g' /etc/postfix/main.cf
echo '[smtp.sendgrid.net]:2525 empowerttl:3mp0wER@dm1n' > /etc/postfix/sasl_passwd
postmap /etc/postfix/sasl_passwd
rm -rf /etc/postfix/sasl_passwd
chmod 600 /etc/postfix/sasl_passwd.db
systemctl restart postfix
systemctl enable postfix

# SETUP MYSQL

systemctl enable mysqld
service mysqld start
mysql_secure_installation

mysql -p -u root
- create database sym_server;
- CREATE USER 'sym_sys'@'localhost' IDENTIFIED BY 'sysP@$$WORDADM1N';
- GRANT ALL PRIVILEGES ON sym_server.* TO 'sym_sys'@'localhost' WITH GRANT OPTION;
nano /etc/my.cnf
echo 'wait_timeout=25920000' >> /etc/my.cnf
service mysqld restart

# JENKINS SERVER CONFIG 
rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
cd /etc/yum.repos.d/
curl -O https://pkg.jenkins.io/redhat-stable/jenkins.repo
dnf install -y jenkins
adduser jenkins sudo
usermod -a -G sudo jenkins
sed -i 's/JENKINS_PORT="8080"/JENKINS_PORT="17505"/g' /etc/sysconfig/jenkins
echo '%jenkins        ALL=(ALL)       NOPASSWD: ALL' >> /etc/sudoers
systemctl enable jenkins
systemctl start jenkins

# WILDFLY SETUP
useradd -m wildfly
usermod -a -G wildfly jenkins
cd /root
curl -O https://download.jboss.org/wildfly/19.0.0.Final/wildfly-19.0.0.Final.tar.gz
sha1sum wildfly-19.0.0.Final.tar.gz | grep 0d47c0e8054353f3e2749c11214eab5bc7d78a14
tar xf wildfly-19.0.0.Final.tar.gz -C  /opt/
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
chmod -R ug+wr /opt/wildfly/standalone/deployments/
cp /home/tkaviya/empowerttl.jks /opt/wildfly/standalone/configuration/
chown wildfly:wildfly /opt/wildfly/standalone/configuration/empowerttl.jks

# add jenkins to wildfly group for deployment
usermod -a -G wildfly jenkins
chown -R wildfly:jenkins /opt/wildfly/standalone/deployments/
cp -R /home/tkaviya/com/ /opt/wildfly/modules/system/layers/base/
chown -R wildfly:wildfly /opt/wildfly/modules/system/layers/base/

mkdir -p /var/integration/mtn/
chown -R wildfly:wildfly /var/integration/mtn/

systemctl enable wildfly
systemctl start wildfly



# CONFIGURE iptables
dnf install iptables-save iptables-services
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
iptables-save > /etc/sysconfig/iptables
systemctl enable iptables

# CONFIGURE APACHE SERVER
dnf install -y httpd php
echo "KeepAlive Off" >> /etc/httpd/conf/httpd.conf
#nano /etc/httpd/conf/sites-available/default-ssl.conf
#-ServerAdmin admin@empowerttl.com
#nano /etc/apache2/sites-available/000-default.conf
#-ServerAdmin admin@empowerttl.com
mkdir -p /var/www/html/symbiosis
chown -R jenkins:jenkins /var/www/html/symbiosis
#a2dismod mpm_event
#a2enmod mpm_prefork 
sed -i 's/Listen 80/Listen 8008/g' /etc/httpd/conf/httpd.conf
systemctl enable httpd
systemctl restart httpd


# CREATE SSL KEYSTORE

#CONFIGURE PGP
cp -R /opt/wildfly/modules/system/layers/base/org/ /opt/wildfly/modules/system/layers/base/
chown wildfly:wildfly /opt/wildfly/modules/system/layers/base/org/bouncycastle/main/*
service wildfly restart && tail -f /opt/wildfly/standalone/log/server.log

#CONFIGURE ANDROID
mkdir -p /opt/android-sdk/
cd /opt/android-sdk/
wget https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip
unzip sdk-tools-linux-4333796.zip
alternatives --config java
- 2
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

keytool -genkey -v -keystore /root/application.keystore -alias sym_mobile -keyalg RSA -keysize 2048 -validity 10000