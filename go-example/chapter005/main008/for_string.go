package main

import "fmt"

func main() {
	str := "Go is a beautiful language!"
	fmt.Printf("字符串的长度为: %d\n", len(str))
	for ix := 0; ix < len(str); ix++ {
		fmt.Printf("位置 %d 的字符是: %c \n", ix, str[ix])
	}
	str2 := "简体中文"
	fmt.Printf("字符串的长度为: %d\n", len(str2))
	for ix := 0; ix < len(str2); ix++ {
		fmt.Printf("位置 %d 的字符是: %c \n", ix, str2[ix])
	}
}
