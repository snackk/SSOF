all:
	javac `find . -name "*.java"`
run:
	#java app.App -ansiColor=true -sqli tests/sqli_01.txt tests/sqli_02.txt tests/sqli_03.txt tests/sqli_04.txt tests/sqli_05.txt
	java app.App -ansiColor=true -xss tests/xss_01.txt tests/xss_02.txt tests/xss_03.txt
clean:
	rm -rf `find . -name "*.class"`
jar:
	jar cf ssof.jar .
content_jar:
	jar tf ssof.jar
