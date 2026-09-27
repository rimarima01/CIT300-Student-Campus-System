@echo off
setlocal
cd /d "%~dp0"

if not exist out mkdir out

echo Compiling application and available tests...
javac -d out src\*.java tests\*.java
if errorlevel 1 goto :failed

echo.
echo Running project smoke test...
call :runTest DataStructureSmokeTest
if errorlevel 1 goto :failed

echo.
echo Running member test suites when they are available...
call :runTest StudentLinkedListTest
if errorlevel 1 goto :failed
call :runTest StackQueueTest
if errorlevel 1 goto :failed
call :runTest StudentIndexTest
if errorlevel 1 goto :failed
call :runTest CampusGraphTest
if errorlevel 1 goto :failed

echo.
echo PASS: all available test suites completed successfully.
echo Any SKIP above means that member test file is not present in this checkout yet.
pause
exit /b 0

:runTest
if not exist "out\%~1.class" (
    echo SKIP: %~1 is not present yet.
    exit /b 0
)
echo Testing %~1...
java -cp out %~1
if errorlevel 1 exit /b 1
exit /b 0

:failed
echo.
echo FAIL: compilation or a test failed. Do not merge until the issue is fixed.
pause
exit /b 1
