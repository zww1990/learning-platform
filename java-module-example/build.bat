@echo off

:: ɾ���ļ���
rd /s /q bin
rd /s /q lib

:: �����ļ���
echo ���ڴ����ļ���
md bin\com.example.demo
md bin\com.example.demo.api
md bin\com.example.demo.app
md lib

:: ����javaԴ�ļ�����ȫ·��д�뵽��ʱ�ļ���
dir /s /b com.example.demo | find ".java" > bin\com.example.demo.tmp
dir /s /b com.example.demo.api | find ".java" > bin\com.example.demo.api.tmp
dir /s /b com.example.demo.app | find ".java" > bin\com.example.demo.app.tmp

:: ����java(ע�����˳��)
echo ���ڱ���JavaԴ�ļ�
javac -encoding utf-8 -d bin\com.example.demo.api @bin\com.example.demo.api.tmp
javac -encoding utf-8 -p bin\com.example.demo.api -d bin\com.example.demo @bin\com.example.demo.tmp
javac -encoding utf-8 -p bin\com.example.demo.api -d bin\com.example.demo.app @bin\com.example.demo.app.tmp

:: ����javaģ��
echo ��ʼ����Javaģ��
java -p bin\com.example.demo;bin\com.example.demo.api -m com.example.demo/com.example.demo.HelloWorld
java -p bin\com.example.demo;bin\com.example.demo.api;bin\com.example.demo.app -m com.example.demo.app/com.example.demo.app.HelloApplication

:: ���
echo ���ڴ��Javaģ��
jar -c -f lib\com.example.demo-api.jar -C bin\com.example.demo.api .
jar -c -f lib\com.example.demo.jar -e com.example.demo.HelloWorld -C bin\com.example.demo .
jar -c -f lib\com.example.demo-app.jar -e com.example.demo.app.HelloApplication -C bin\com.example.demo.app .

:: ����jarģ��
echo ��ʼ����������
java -p lib -m com.example.demo
java -p lib -m com.example.demo.app

pause
