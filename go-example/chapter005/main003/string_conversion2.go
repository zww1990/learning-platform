package main

import (
	"fmt"
	"strconv"
)

func main() {
	var orig = "ABC"
	var newS string

	fmt.Printf("int的大小为: %d\n", strconv.IntSize)
	// anInt, err = strconv.Atoi(origStr)
	an, err := strconv.Atoi(orig)
	if err != nil {
		fmt.Printf("orig %s 不是一个整数 - 退出时出错 - [%s]\n", orig, err)
		return
	}
	fmt.Printf("该整数为 %d\n", an)
	an = an + 5
	newS = strconv.Itoa(an)
	fmt.Printf("新的字符串是: %s\n", newS)
}
