FROM eclipse-temurin:17-jdk

ARG USERNAME=scotthal
ARG USER_UID=1100
ARG USER_GID=$USER_UID

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update; \
  apt-get -y install locales less dialog apt-utils man-db bc zsh zip unzip sudo vim git lsb-release curl httpie jq; \
  apt-get -y dist-upgrade; \
  echo 'en_US.UTF-8 UTF-8' >> /etc/locale.gen; \
  locale-gen; \
  update-locale LANG=en_US.UTF-8; \
  curl -L https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz | gzip -dc | tar -C /opt/java -xf - ; \
  mv /opt/java/apache-maven-* /opt/java/maven; \
  groupadd --gid $USER_GID $USERNAME; \
  curl -L https://services.gradle.org/distributions/gradle-8.3-bin.zip > /tmp/gradle-bin.zip; \
  unzip /tmp/gradle-bin.zip -d /opt/java; \
  mv /opt/java/gradle-* /opt/java/gradle; \
  useradd -s /bin/zsh --uid $USER_UID --gid $USER_GID -m $USERNAME; \
  echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME; \
  chmod 440 /etc/sudoers.d/$USERNAME; \  apt-get autoremove -y; \
  apt-get clean -y; \
  rm -rf /var/lib/apt/lists/*

ENV DEBIAN_FRONTEND=

USER ${USERNAME}

CMD ["/bin/zsh", "-l"]
