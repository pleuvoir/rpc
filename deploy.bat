@echo on
@echo =============================================================
@echo $                                                           $
@echo $                      rpc				  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title rpc deploy
@color 0a

rem  Please execute command in local directory.

call mvn clean deploy -DskipTests -P release

pause

pause