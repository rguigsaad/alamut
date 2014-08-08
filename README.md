alamut
=========

Dillinger is a cloud-enabled HTML5 Markdown editor.

  - Install jetty standalone app and start jetty. 
```sh
java -jar start.jar OPTIONS=All
```
  - Change in pom.xml the : jetty.webapp.directory
  - Run command : 
 ```sh
mvn clean install
```
  - Go check http://localhost:8080/alamut/rest/user
