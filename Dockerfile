FROM chandraprakashreddy/applications:rhel-maven-oraclejdk-1.0

ENV JAVA_HOME=/jdk1.8.0_221/jre
ENV PATH=/jdk1.8.0_221/bin:$PATH
ENV PATH=/apache-maven-3.5.4/bin:$PATH

RUN mkdir employee_registry_backend
RUN mkdir employee_registry_backend/src

ADD ./src /employee_registry_backend/src/
ADD ./pom.xml /employee_registry_backend/pom.xml

RUN mvn -f /employee_registry_backend/pom.xml clean install

ENTRYPOINT [ "sh", "-c", " nohup java -XX:-HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/dumps/ -Djava.security.egd=file:/dev/./urandom -jar /employee_registry_backend/target/*.jar --server.port=8080" ]