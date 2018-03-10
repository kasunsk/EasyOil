
Installation Guide
==================

* Run **gradle clean build** in linux terminal if gradle installed.

* Run **./gradlew clean build** in linux terminal  if gradle not installed.

* import or run easy_oil.sql in /db to add database.

Configuration
=============

* Change mysql database username (spring.datasource.username) and password 
spring.datasource.password in application.properties configuration (in /resources).


Deployment
==========

Rnn **java -jar build/libs/EasyOil-0.0.1-SNAPSHOT.jar** in linux terminal
