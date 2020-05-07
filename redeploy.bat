@ECHO OFF

CD /D .\tomcat\conf\Catalina\localhost
COPY /b app.xml +,, >nul
IF %ERRORLEVEL% == 0 ECHO Done.

