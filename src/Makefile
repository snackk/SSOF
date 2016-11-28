all:
	javac `find . -name "*.java"`
run:
	java app.App -ansiColor=true sqli_01.txt sqli_02.txt sqli_03.txt sqli_04.txt sqli_05.txt
clean:
	rm -rf `find . -name "*.class"`
jar:
	jar cf ssof.jar .
content_jar:
	jar tf ssof.jar