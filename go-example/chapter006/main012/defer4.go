package main

import "fmt"

func main() {
	doDBOperations()
}

func connectToDB() {
	fmt.Println("好的，连接到数据库")
}

func disconnectFromDB() {
	fmt.Println("好的，与数据库断开连接")
}

func doDBOperations() {
	connectToDB()
	fmt.Println("延迟数据库断开连接。")
	defer disconnectFromDB() //这里调用的函数带有defer
	fmt.Println("执行一些数据库操作 ...")
	fmt.Println("糟了！一些崩溃或网络错误 ...")
	fmt.Println("从这里回来！")
	return //终止程序
	// 延迟函数在实际返回之前执行，即使在此之前有返回或异常终止
}
