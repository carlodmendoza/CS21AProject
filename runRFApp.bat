@echo off

set /p name="Enter the name of your Relief Center: "

cd src
java -cp ../lib/*;../out/ RFApp %name%
cmd /k