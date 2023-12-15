package main

import "fmt"

func main() {
	s := "good bye"
	var p *string = &s
	*p = "ciao"
	fmt.Printf("这里是指针 p: %p\n", p)    // 打印地址
	fmt.Printf("这里是字符串 *p: %s\n", *p) // 打印字符串
	fmt.Printf("这里是字符串 s: %s\n", s)   // 打印相同的字符串
}
