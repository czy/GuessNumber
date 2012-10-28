#!/bin/sh
mvn install -Dmaven.test.skip=true
cd target\dist
java -jar cn.beihangsoft.game-1.0.jar
