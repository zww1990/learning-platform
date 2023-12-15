package main

import "fmt"

func main() {
	i := 0
	for { //由于没有检查，这是一个无限循环
		if i >= 3 {
			break
		}
		//当满足此条件时，中断此for循环
		fmt.Println("i的值为:", i)
		i++
	}
	fmt.Println("紧接着for循环的语句。")
}
