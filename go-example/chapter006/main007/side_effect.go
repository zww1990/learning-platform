package main

import (
	"fmt"
)

// Multiply 方法更改 reply:
func Multiply(a, b int, reply *int) {
	*reply = a * b
}

func main() {
	n := 0
	reply := &n
	Multiply(10, 5, reply)
	fmt.Println("乘法:", *reply) // 乘法: 50
}
