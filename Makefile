.PHONY: all compile package run clean

all: compile

compile:
	mvn compile

package:
	mvn package

run:
	mvn exec:java -Dexec.mainClass="com.group_30_lab3_2024.App" -Dexec.args="$(ARGS)"

clean:
	mvn clean
