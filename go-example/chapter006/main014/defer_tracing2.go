package main

import "fmt"

func trace(s string) string {
	fmt.Println("进入:", s)
	return s
}

func un(s string) {
	fmt.Println("离开:", s)
}

func a() {
	defer un(trace("a"))
	fmt.Println("在 a 中")
}

func b() {
	defer un(trace("b"))
	fmt.Println("在 b 中")
	a()
}

func main() {
	b()
}
