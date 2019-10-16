# employee_registry_backend [![Build Status](https://dev.azure.com/chandra-prakash-reddy/EmployeeRegistry/_apis/build/status/chandra-prakash-reddy.employee_registry_backend?branchName=master)](https://dev.azure.com/chandra-prakash-reddy/EmployeeRegistry/_build/latest?definitionId=3&branchName=master) [![Maven Central](https://maven-badges-generator.herokuapp.com/maven-central/com.github.chandra-prakash-reddy/employee-registry/badge.svg?color=orange)](https://repo1.maven.org/maven2/com/github/chandra-prakash-reddy/employee-registry)


SpringBoot Application which  performs CRUD operations on employee registry data

# prerequisites # 
   * Install Java8
      * installation  : https://www.oracle.com/technetwork/java/jdk8-downloads-2133151.html
      * documentation : https://docs.oracle.com/javase/8/docs/api
   * SpringBoot sources
      * documentaion : https://spring.io/docs
   * Maven
      * installation  : https://maven.apache.org/download.cgi
      * documentation : https://maven.apache.org/guides


# Run #
   * move to project root directory
   * run ***mvn verify*** 
   * run ***java -jar target/employee-registry --server.port=<port\>***
      * example: java -jar target/employee-registry --server.port=7878
  
  # Docker Run #
   * ***docker run command :***
      * ***docker run -p \<port>:8080 --name <container_name> chandraprakashreddy/applications:employee-registry-services-v1.01***
  
   * ***run arguments :***
      * \<port> : provide the port number on which it should be run the process
      * <container_name> : provide the container name
# pom.xml #
   Find the pom dependency reference here [pom.xml](https://search.maven.org/artifact/com.github.chandra-prakash-reddy/employee-registry/1.1/jar "pom.xml")

# License #
   This project is licensed under the MIT License - see the [License](https://opensource.org/licenses/MIT "License")  for details
