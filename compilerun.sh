#!/bin/bash
javac -d bin -sourcepath src/  src/main/Application.java 
java -classpath bin/ main/Application
