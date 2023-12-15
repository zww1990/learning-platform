package main

import "fmt"

func main() {
	var i1 = 5
	fmt.Printf("一个整数: %d, 它在内存中的位置: %p\n", i1, &i1)
	var intP *int
	intP = &i1
	fmt.Printf("内存位置 %p 的值是 %d\n", intP, *intP)
}
