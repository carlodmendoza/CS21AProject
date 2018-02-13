@ECHO OFF
rem javac -cp ./+libs/* -d ./out/ *.java
rem pause


IF NOT EXIST "%CD%"\out\ (
	ECHO log and out does not exist, creating . . .
	MD log
	MD out
	javac -cp ./lib/* -d ./out/ src/*.java
	PAUSE
) ELSE (
	javac -cp ./lib/* -d ./out/ src/*.java
	PAUSE
)