@echo off
:Begin
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]���ڼ�������Ƿ�ͨ��
ping -n 5 www.baidu.com > nul
if errorlevel 1 goto Reboot
if errorlevel 0 goto Continue
:Continue
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]����ͨ��
timeout /t 5 > nul
goto Begin
:Reboot
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]���粻ͨ��,����������...
netsh wlan disconnect
netsh wlan connect name=CMCC-weiwei
echo [%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%]����ָ�ͨ��
timeout /t 5 > nul
goto Begin
