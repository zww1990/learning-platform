package main

import "fmt"

func main() {
	var i = 5
	for {
		i = i - 1
		fmt.Printf("变量i的值现在是: %d\n", i)
		if i < 0 {
			break
		}
	}
}
