@ECHO OFF

CALL setenv.bat

IF EXIST %~dp0webapp RMDIR %~dp0webapp /s /q

MKDIR %~dp0webapp

XCOPY %~dp0..\%PROJECT_NAME%\%PROJECT_WEB_DIR% %~dp0webapp /s /e /y /i >nul
XCOPY %~dp0..\%PROJECT_NAME%\%CLASSES_DIR% %~dp0webapp\WEB-INF\classes /s /e /y /i >nul

IF %ERRORLEVEL% == 0 ECHO Done.