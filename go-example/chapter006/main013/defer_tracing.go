package main

import "fmt"

func trace(s string)   { fmt.Println("进入:", s) }
func untrace(s string) { fmt.Println("离开:", s) }

func a() {
	trace("a")
	defer untrace("a")
	fmt.Println("在 a 中")
}

func b() {
	trace("b")
	defer untrace("b")
	fmt.Println("在 b 中")
	a()
}

func main() {
	b()
}
