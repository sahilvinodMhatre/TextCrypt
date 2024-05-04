FROM openjdk:8

ENV JAVA_HOME /opt/jdk
ENV PATH ${PATH}:${JAVA_HOME}/bin   
RUN apt-get update && apt-get install -y libxext6 libxrender1 libxtst6

COPY TextCrypt.jar /usr/local/TextCrypt
ENTRYPOINT ["java","-jar","/usr/local/TextCrypt/TextCrypt.jar"]
