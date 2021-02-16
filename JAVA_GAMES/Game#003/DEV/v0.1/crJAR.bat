@ECHO OFF
ECHO.
ECHO KOMPILIERE DATEIEN... bitte warten.
javac *.java
ECHO.
ECHO ERSTELLE RELEASE-DATEI "CatchIt.jar"... bitte warten.
jar cvfm RELEASE/CatchIt.jar Manifest.txt *.class
ECHO.
ECHO TEST-START... bitte warten.
java -jar RELEASE/CatchIt.jar