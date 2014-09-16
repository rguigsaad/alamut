alamut
=========

  - Run H2 db, (create a database 'alamut') and execute script of table creation .
```sh
java -jar h2*.jar
```
  Get :
  http://localhost:8080/user?emailAdress=<email adress you look for>
  Post:  should send a json like  : {"userName":"srguig","emailAddress":"email@gmail.com","phoneNumber":"0123234"}
