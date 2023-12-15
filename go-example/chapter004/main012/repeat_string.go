package main

import (
	"fmt"
	"strings"
)

func main() {
	var origS = "Hi there! "
	var newS string

	newS = strings.Repeat(origS, 3)
	fmt.Printf("新的重复字符串是: %s\n", newS)
}
