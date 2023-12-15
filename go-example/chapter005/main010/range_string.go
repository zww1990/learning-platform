package main

import "fmt"

func main() {
	str := "Go is a beautiful language!"
	fmt.Printf("字符串的长度为: %d\n", len(str))
	for pos, char := range str {
		fmt.Printf("位置 %d 的字符是: %c \n", pos, char)
	}
	fmt.Println()
	str2 := "Chinese: 简体中文"
	fmt.Printf("字符串的长度为: %d\n", len(str2))
	for pos, char := range str2 {
		fmt.Printf("字符 %c 开始于字节位置 %d\n", char, pos)
	}
	fmt.Println()
	fmt.Println("索引 int(rune) rune    字符 字节")
	for index, rune := range str2 {
		fmt.Printf("%-2d      %d      %U '%c' % X\n", index, rune, rune, rune, []byte(string(rune)))
	}
}
