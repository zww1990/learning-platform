package main

import "fmt"

func main() {
	function1()
}

func function1() {
	fmt.Printf("在顶部的函数1中\n")
	defer function2()
	fmt.Printf("在底部的函数1中！\n")
}

func function2() {
	fmt.Printf("函数2：延迟到调用函数结束！\n")
}
