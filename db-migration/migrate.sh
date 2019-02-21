#!/bin/bash
java -jar ./target/db-migration-jar-with-dependencies.jar --url='jdbc:postgresql://localhost/horns_and_hooves' --username=jboss --changeLogFile='com/horns_and_hooves/db/changelog/changelog-master.xml' --logLevel=debug  $1 $2
