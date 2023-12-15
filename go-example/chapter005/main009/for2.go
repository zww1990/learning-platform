package main

import "fmt"

func main() {
	var i = 5

	for i >= 0 {
		i = i - 1
		fmt.Printf("变量i现在是: %d\n", i)
	}
}
