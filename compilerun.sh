#!/bin/bash
javac -d bin -sourcepath src/ -cp lib/sqlite-jdbc-3.21.0.jar src/main/Application.java 
java  -classpath bin:./lib/sqlite-jdbc-3.21.0.jar main.Application
