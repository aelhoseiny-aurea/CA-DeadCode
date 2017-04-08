FROM ubuntu:14.04

# Install Java 8
RUN apt-get update
RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:webupd8team/java -y
RUN apt-get update
RUN echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections
RUN apt-get install oracle-java8-installer -y --force-yes
RUN apt-get install oracle-java8-set-default

#install Understand
RUN wget wget http://builds.scitools.com/all_builds/b844/Understand/Understand-4.0.844-Linux-64bit.tgz && tar -xvzf Understand-4.0.844-Linux-64bit.tgz
RUN echo "Server: scitools-license.devfactory.com 00000000 9000" >scitools/conf/locallicense.dat

#configure dependencies as in the training video
# i will do it after the run if needed

ENV STIHOME /scitools
ENV LD_LIBRARY_PATH /usr/lib/jvm/java-8-oracle/jre/lib/amd64/:/usr/lib/jvm/java-8-oracle/jre/lib/amd64/server:/scitools/bin/linux64/Java:/scitools/bin/linux64/python/
ENV PYTHONPATH /scitools/bin/linux64/python
 
#Install aws cli
RUN apt-get install awscli -y

# Configure  env
RUN mkdir app
VOLUME /tmp
COPY ca-deadcode-0.0.1-SNAPSHOT.jar /app/api.jar

RUN sh -c 'touch /app/app.jar'
ENV JAVA_OPTS=""
EXPOSE 80 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app/api.jar" ]
