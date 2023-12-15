package main

import "fmt"

func main() {
	var i1 int
	var f1 float32
	i1, _, f1 = ThreeValues()
	fmt.Printf("整数型: %d, 浮点型: %f \n", i1, f1)
}

func ThreeValues() (int, int, float32) {
	return 5, 6, 7.5
}
