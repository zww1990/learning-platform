package main

import "fmt"

func main() {
	var n int16 = 34
	var m int32
	// 编译器错误:无法在赋值中将n（类型int16）用作类型int32
	//m = n
	m = int32(n)

	fmt.Printf("32 bit int is: %d\n", m)
	fmt.Printf("16 bit int is: %d\n", n)
}
