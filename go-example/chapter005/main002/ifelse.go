package main

import "fmt"

func main() {
	var first = 10
	var cond int

	if first <= 0 {
		fmt.Printf("first 小于或等于0\n")
	} else if first > 0 && first < 5 {
		fmt.Printf("first 是在0到5之间\n")
	} else {
		fmt.Printf("first 为5或更大\n")
	}
	if cond = 5; cond > 10 {
		fmt.Printf("cond 大于10\n")
	} else {
		fmt.Printf("cond 不大于10\n")
	}
}
