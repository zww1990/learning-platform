package main

import "fmt"

var num = 10
var numx2, numx3 int

func main() {
	numx2, numx3 = getX2AndX3(num)
	PrintValues()
	numx2, numx3 = getX2AndX3_2(num)
	PrintValues()
}

func PrintValues() {
	fmt.Printf("num = %d, 2x num = %d, 3x num = %d\n", num, numx2, numx3)
}

func getX2AndX3(input int) (int, int) {
	// 非命名返回值：需要在return语句中指定返回的值
	return 2 * input, 3 * input
}

func getX2AndX3_2(input int) (x2 int, x3 int) {
	// 命名返回值：return语句中可以不用指定返回的值，return关键字一定要有
	x2 = 2 * input
	x3 = 3 * input
	//return x2, x3
	return
}
