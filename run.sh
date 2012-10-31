#!/bin/sh
mvn install -Dmaven.test.skip=true
cd target/dist
java -jar guessnumber-1.0.jar
