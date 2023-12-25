package main

import (
	"fmt"
)

func main() {
	fmt.Printf("%d 是偶数: 是 %t\n", 16, even(16)) // 16是偶数：是真的
	fmt.Printf("%d 是奇数: 是 %t\n", 17, odd(17))  // 17是奇数：是真的
	fmt.Printf("%d 是奇数: 是 %t\n", 18, odd(18))  // 18是奇数：是假的
}

func even(nr int) bool {
	if nr == 0 {
		return true
	}
	return odd(RevSign(nr) - 1)
}

func odd(nr int) bool {
	if nr == 0 {
		return false
	}
	return even(RevSign(nr) - 1)
}

func RevSign(nr int) int {
	if nr < 0 {
		return -nr
	}
	return nr
}
