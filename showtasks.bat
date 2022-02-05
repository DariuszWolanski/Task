call runcrud.bat
if "%ERRORLEVEL%" == "0" goto getTasks
echo.
echo Things has errors – breaking work
goto fail

:getTasks
call http://localhost:8080/crud/v1/task
if "%ERRORLEVEL%" == "0" goto end
echo Browser was breaking :)

:end
echo.
echo Successfull

:fail
echo.
echo Unsuccessful


