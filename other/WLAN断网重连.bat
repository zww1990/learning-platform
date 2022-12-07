@echo off
:Begin
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]正在检测网络是否通畅
ping -n 5 www.baidu.com > nul
if errorlevel 1 goto Reboot
if errorlevel 0 goto Continue
:Continue
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]网络通畅
timeout /t 5 > nul
goto Begin
:Reboot
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]网络不通畅,正在重连中...
netsh wlan disconnect
netsh wlan connect name=CMCC-weiwei
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]网络恢复通畅
timeout /t 5 > nul
goto Begin
