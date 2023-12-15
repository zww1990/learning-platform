package main

import "fmt"

func main() {
	k := 6
	switch k {
	case 4:
		fmt.Println("是 <= 4")
		fallthrough
	case 5:
		fmt.Println("是 <= 5")
		fallthrough
	case 6:
		fmt.Println("是 <= 6")
		fallthrough
	case 7:
		fmt.Println("是 <= 7")
		fallthrough
	case 8:
		fmt.Println("是 <= 8")
		fallthrough
	default:
		fmt.Println("默认情况")
	}
}
