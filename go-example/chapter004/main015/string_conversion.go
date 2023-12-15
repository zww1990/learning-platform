package main

import (
	"fmt"
	"strconv"
)

func main() {
	var orig = "666"
	var an int
	var newS string

	fmt.Printf("int的大小为: %d\n", strconv.IntSize)

	an, _ = strconv.Atoi(orig)
	fmt.Printf("该整数为: %d\n", an)
	an = an + 5
	newS = strconv.Itoa(an)
	fmt.Printf("新的字符串是: %s\n", newS)
}
