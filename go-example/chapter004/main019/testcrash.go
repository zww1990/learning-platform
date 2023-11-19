package main

import "fmt"

func main() {
	var p *int = nil
	*p = 0
	fmt.Println(p)
	fmt.Println(*p)
}
