call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:browser
start http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There where errors

:end
echo.
echo Work is finished.