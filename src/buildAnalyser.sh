#!


javac -d ../analyser/ -classpath ".:../lib/*" tester.java

cd ../analyser/
java -classpath ".:../lib/*" tester
