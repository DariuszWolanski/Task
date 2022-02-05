call runcrud.bat
if "%ERRORLEVEL%" == "0" goto getTasks
echo.
echo Cannot run crud.war
goto fail

:getTasks
call http://localhost:8080/crud/v1/task
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open browser

:end
echo.
echo Successfull

:fail
echo.
echo Unsuccessful


