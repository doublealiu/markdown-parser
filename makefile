test: MarkdownParse.class MarkdownParseTest.class
	java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest

MarkdownParse.class: MarkdownParse.java
	echo "Main compiled"
	javac MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java
	echo "TEst compiled"
	javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java