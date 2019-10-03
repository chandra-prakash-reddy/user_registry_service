# employee_registry_backend
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
        example: java -jar target/employee-registry --server.port=7878
   * open browser path: ***http://localhost:7878/h2***
   * login ***user:admin  password:admin***
   * install datamodel in h2 with following file content (copy and paste in console and click run icon)
        https://github.com/chandra-prakash-reddy/employee_registry_backend/blob/master/src/main/resources/employee-registry.sql
  
  
