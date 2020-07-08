FROM ubuntu:18.04

ENV DEBIAN_FRONTEND noninteractive
ENV TKAVIYA_HOME /home/tkaviya

# prevent services from starting automatically
RUN echo exit 0 > /usr/sbin/policy-rc.d

# restore all persistence data
VOLUME ["/etc/default/", "/etc/mysql/", "/etc/postfix/", "/root/", "/home/", "/opt/", "/var/lib/jenkins/", "/var/lib/mysql/", "/var/log/"]

RUN mkdir -p /root/installers/ && \
    mkdir -p /root/certificates/

# install general server programs
RUN apt-get update -y && \
    apt-get install -y wget mlocate net-tools nmap telnet links unzip unrar man git dbus systemd nano dialog apt-utils \
                       mlocate telnet dnsutils bzip2 binutils openjdk-8-jdk openjdk-11-jdk sudo curl \
                       openssh-server gnupg2 npm libsasl2-modules && \
    apt-get autoremove -y && \
    apt-get clean -y
    #rm -rf /var/lib/apt/lists/*
    #timedatectl set-timezone Africa/Accra

# install jenkins
RUN apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 9B7D32F2D50582E6 && \
    wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | apt-key add - && \
    sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list' && \
    apt-get update -y && \
    apt-get install -y jenkins && \
    chown -R jenkins:jenkins /var/lib/jenkins && \
    adduser jenkins sudo && \
    sed -i 's/HTTP_PORT=8080/HTTP_PORT=17505/g' /etc/default/jenkins && \
    systemctl enable jenkins

CMD ['service jenkins restart']

# create user tkaviya
RUN useradd -m tkaviya -s /bin/bash -c "Tsungai Kaviya" && \
    useradd -m pmutyambizi -s /bin/bash -c "Paul Mutyambizi" && \
    echo 'tkaviya:k@viya' | chpasswd && \
    echo 'pmutyambizi:annatis' | chpasswd && \
    echo 'root:3mp0wER@dm1n' | chpasswd && \
    usermod -a -G adm tkaviya && \
    usermod -a -G video tkaviya && \
    adduser tkaviya sudo && \
    usermod -a -G adm pmutyambizi && \
    usermod -a -G video pmutyambizi && \
    adduser pmutyambizi sudo

# configure ssh
RUN mkdir -p /var/run/sshd && \
    chmod 755 /var/run/sshd && \
    sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config && \
    sed -ri 's/#PasswordAuthentication yes/PasswordAuthentication yes/g' /etc/ssh/sshd_config && \
    sed -ri 's/PasswordAuthentication no/PasswordAuthentication yes/g' /etc/ssh/sshd_config && \
    echo "KexAlgorithms curve25519-sha256,curve25519-sha256@libssh.org,diffie-hellman-group16-sha512,diffie-hellman-group18-sha512,diffie-hellman-group-exchange-sha256" >> /etc/ssh/sshd_config && \
    echo "Ciphers chacha20-poly1305@openssh.com,aes256-gcm@openssh.com,aes128-gcm@openssh.com,aes256-ctr,aes192-ctr,aes128-ctr" >> /etc/ssh/sshd_config && \
    echo "MACs hmac-sha2-256-etm@openssh.com,hmac-sha2-512-etm@openssh.com,umac-128-etm@openssh.com" >> /etc/ssh/sshd_config && \
    echo "HostKeyAlgorithms ssh-ed25519,ssh-ed25519-cert-v01@openssh.com" >> /etc/ssh/sshd_config && \
    mkdir -p /home/tkaviya/.ssh && \
    chown -R tkaviya:tkaviya /home/tkaviya/.ssh && \
    chmod 700 /home/tkaviya/.ssh && \
    mkdir -p /home/pmutyambizi/.ssh && \
    chown -R pmutyambizi:pmutyambizi /home/pmutyambizi/.ssh && \
    chmod 700 /home/pmutyambizi/.ssh && \
    service ssh restart && \
    systemctl enable ssh

CMD    ["/usr/sbin/sshd", "-D"]

RUN updatedb

# expose required ports
EXPOSE 22 80 443 8008 8080 8443 17505
