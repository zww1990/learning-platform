package main

import "fmt"

func main() {
	var ch int = '\u0041'
	var ch2 int = '\u03B2'
	var ch3 int = '\U00101234'
	fmt.Printf("%d - %d - %d\n", ch, ch2, ch3) // 整数
	fmt.Printf("%c - %c - %c\n", ch, ch2, ch3) // 字符
	fmt.Printf("%X - %X - %X\n", ch, ch2, ch3) // UTF-8 字节
	fmt.Printf("%U - %U - %U", ch, ch2, ch3)   // UTF-8 码位
}
