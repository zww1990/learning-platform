package main

import "fmt"

func main() {
	var num1 = 100

	switch num1 {
	case 98, 99:
		fmt.Println("它等于98")
	case 100:
		fmt.Println("它等于100")
	default:
		fmt.Println("它不等于98或100")
	}
}
