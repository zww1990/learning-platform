@echo off

:: 删除文件夹
rd /s /q bin
rd /s /q lib

:: 创建文件夹
echo 正在创建文件夹
md bin\com.example.demo
md bin\com.example.demo.api
md bin\com.example.demo.app
md lib

:: 搜索java源文件并将全路径写入到临时文件中
dir /s /b com.example.demo | find ".java" > bin\com.example.demo.tmp
dir /s /b com.example.demo.api | find ".java" > bin\com.example.demo.api.tmp
dir /s /b com.example.demo.app | find ".java" > bin\com.example.demo.app.tmp

:: 编译java(注意编译顺序)
echo 正在编译Java源文件
javac -encoding utf-8 -d bin\com.example.demo.api @bin\com.example.demo.api.tmp
javac -encoding utf-8 -p bin\com.example.demo.api -d bin\com.example.demo @bin\com.example.demo.tmp
javac -encoding utf-8 -p bin\com.example.demo.api -d bin\com.example.demo.app @bin\com.example.demo.app.tmp

:: 运行java模块
echo 开始运行Java模块
java -p bin\com.example.demo;bin\com.example.demo.api -m com.example.demo/com.example.demo.HelloWorld
java -p bin\com.example.demo;bin\com.example.demo.api;bin\com.example.demo.app -m com.example.demo.app/com.example.demo.app.HelloApplication

:: 打包
echo 正在打包Java模块
jar -c -f lib\com.example.demo-api.jar -C bin\com.example.demo.api .
jar -c -f lib\com.example.demo.jar -e com.example.demo.HelloWorld -C bin\com.example.demo .
jar -c -f lib\com.example.demo-app.jar -e com.example.demo.app.HelloApplication -C bin\com.example.demo.app .

:: 运行jar模块
echo 开始运行主程序
java -p lib -m com.example.demo
java -p lib -m com.example.demo.app

pause
