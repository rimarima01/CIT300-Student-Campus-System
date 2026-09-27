@echo off
setlocal
cd /d "%~dp0"

if not exist out mkdir out

echo Compiling CIT300 Student and Campus System...
javac -d out src\*.java
if errorlevel 1 goto :failed

echo.
echo Starting the application...
java -cp out Main
if errorlevel 1 goto :failed

echo.
echo Application closed.
pause
exit /b 0

:failed
echo.
echo ERROR: Compilation or application startup failed.
echo Check that JDK 17 or later is installed and available as javac/java.
pause
exit /b 1
