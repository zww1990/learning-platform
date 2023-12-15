package main

import "fmt"

func main() {
	s := ""
	for s != "aaaaa" {
		fmt.Println("s的值是:", s)
		s = s + "a"
	}
}
