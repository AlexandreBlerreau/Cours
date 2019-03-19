#!/bin/bash

javac -cp servlet-api.jar:sqlite-jdbc.jar vide/WEB-INF/classes/*.java
sqlite3 bdd < config.sql 2>/dev/null
./../bin/shutdown.sh > /dev/null
./../bin/startup.sh 1>/dev/null && echo "Projet compilé
Base de données prête
Tomcat8 est démarré"
 
