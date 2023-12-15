package main

import (
	"fmt"
	"strings"
)

func main() {
	var orig = "Hey, how are you George?"
	var lower string
	var upper string

	fmt.Printf("原来的字符串是: %s\n", orig)
	lower = strings.ToLower(orig)
	fmt.Printf("小写字符串为: %s\n", lower)
	upper = strings.ToUpper(orig)
	fmt.Printf("大写的字符串为: %s\n", upper)
}
