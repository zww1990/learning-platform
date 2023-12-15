package main

import "fmt"

type TZ int

func main() {
	var a, b TZ = 3, 4
	c := a + b
	fmt.Printf("c 的值: %d", c) // 输出：c 的值: 7
}
