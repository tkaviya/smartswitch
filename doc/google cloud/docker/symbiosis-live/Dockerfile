FROM ubuntu:18.04

ENV DEBIAN_FRONTEND noninteractive

# install general server programs
RUN apt-get update -y && \
    apt-get upgrade -y && \
    apt-get install -y wget mlocate nmap telnet links unzip unrar man gradle git dbus systemd nano \
                       mlocate telnet dnsutils bzip2 binutils openjdk-17-jdk mysql-server sudo curl \
                       openssh-server && \
    apt-get autoremove -y && \
    apt-get clean -y
    #rm -rf /var/lib/apt/lists/*
    #timedatectl set-timezone Africa/Accra

# configure mysql
RUN mkdir /var/run/mysqld && \
    chown -R mysql:mysql /var/run/mysqld

VOLUME ["/var/lib/mysql"]

CMD ["mysqld_safe"]

# install postfix
RUN apt-get install -y postfix libsasl2-modules && \
    postconf -e 'relayhost = [smtp.sendgrid.net]:2525' && \
    postconf -e 'smtp_tls_security_level = encrypt' && \
    postconf -e 'smtpd_sasl_auth_enable = yes' && \
    postconf -e 'smtp_sasl_password_maps = hash:/etc/postfix/sasl_passwd' && \
    postconf -e 'header_size_limit = 4096000' && \
    postconf -e 'smtp_sasl_security_options = noanonymous' && \
    echo '[smtp.sendgrid.net]:2525 [streetsmart.zw]:[$M@RTus3r]' >> /etc/postfix/sasl_passwd && \
    cp /etc/postfix/main.cf.proto /etc/postfix/main.cf && \
    sed -i 's/setgid_group =/#setgid_group = postdrop/g' /etc/postfix/main.cf && \
    postmap /etc/postfix/sasl_passwd && \
    rm /etc/postfix/sasl_passwd && \
    chmod 600 /etc/postfix/sasl_passwd.db && \
    #/etc/init.d/postfix restart && \
    systemctl enable postfix

# configure jenkins

# create user tkaviya
RUN useradd -m tkaviya && \
    echo 'tkaviya:k@viya' | chpasswd && \
    echo 'root:3mp0wER@dm1n' | chpasswd && \
    usermod -a -G adm tkaviya && \
    usermod -a -G video tkaviya && \
    adduser tkaviya sudo

VOLUME ["/root", "/home/tkaviya"]

# configure ssh
RUN mkdir -p /var/run/sshd && \
#    chmod 755 /var/run/sshd && \
    mkdir -p /home/tkaviya/.ssh && \
    chown -R tkaviya:tkaviya /home/tkaviya/.ssh && \
    chmod 700 /home/tkaviya/.ssh && \
    sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config && \
    sed -ri 's/#PasswordAuthentication yes/PasswordAuthentication yes/g' /etc/ssh/sshd_config && \
    sed -ri 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config && \
    echo "KexAlgorithms curve25519-sha256,curve25519-sha256@libssh.org,diffie-hellman-group16-sha512,diffie-hellman-group18-sha512,diffie-hellman-group-exchange-sha256" >> /etc/ssh/sshd_config && \
    echo "Ciphers chacha20-poly1305@openssh.com,aes256-gcm@openssh.com,aes128-gcm@openssh.com,aes256-ctr,aes192-ctr,aes128-ctr" >> /etc/ssh/sshd_config && \
    echo "MACs hmac-sha2-256-etm@openssh.com,hmac-sha2-512-etm@openssh.com,umac-128-etm@openssh.com" >> /etc/ssh/sshd_config && \
    echo "HostKeyAlgorithms ssh-ed25519,ssh-ed25519-cert-v01@openssh.com" >> /etc/ssh/sshd_config && \
    systemctl enable ssh && \
    service ssh restart

EXPOSE 22

CMD    ["/usr/sbin/sshd", "-D"]

# configure wildfly
ENV WILDFLY_VERSION 17.0.1.Final
ENV WILDFLY_SHA1 eaef7a87062837c215e54511c4ada8951f0bd8d5
ENV JBOSS_HOME /opt/wildfly

USER root

RUN useradd -m wildfly && \
    cd $HOME && \
    curl -O https://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz && \
    sha1sum wildfly-$WILDFLY_VERSION.tar.gz | grep $WILDFLY_SHA1 && \
    tar xf wildfly-$WILDFLY_VERSION.tar.gz && \
    mv $HOME/wildfly-$WILDFLY_VERSION $JBOSS_HOME && \
    rm wildfly-$WILDFLY_VERSION.tar.gz && \
    chown -R wildfly:0 ${JBOSS_HOME} && \
    chmod -R g+rw ${JBOSS_HOME}

# Ensure signals are forwarded to the JVM process correctly for graceful shutdown
ENV LAUNCH_JBOSS_IN_BACKGROUND true

VOLUME ["/opt/wildfly", "/var/log"]

EXPOSE 80 443 8080 8443

USER wildfly

CMD ["/opt/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

USER root

RUN updatedb