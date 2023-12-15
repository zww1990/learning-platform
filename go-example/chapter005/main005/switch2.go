package main

import "fmt"

func main() {
	var num1 = 7

	switch {
	case num1 < 0:
		fmt.Println("数字为负")
	case num1 > 0 && num1 < 10:
		fmt.Println("数字介于0和10之间")
	default:
		fmt.Println("数字为10或更大")
	}
}
