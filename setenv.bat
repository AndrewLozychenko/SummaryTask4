@ECHO OFF

::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: YOU HAVE TO SET THE JAVA_HOME
::::::::::::::::::::::::::::::::::::::::::::::::::::::::

:: must point to jre/jdk home
SET JAVA_HOME=E:\Programs\JDK

::::::::::::::::::::::::::::::::::::::::::::::::::::::::
:: DON'T TOUCH THE LINES BELOW
::::::::::::::::::::::::::::::::::::::::::::::::::::::::

IF NOT EXIST "%JAVA_HOME%" (
	ECHO [setenv.bat] The JAVA_HOME variable is not defined correctly.
	EXIT
)

SET PATH=%JAVA_HOME%\bin;%PATH%
SET CATALINA_HOME=%~dp0tomcat
SET CATALINA=%CATALINA_HOME%\bin\catalina.bat
