package main

import (
	"fmt"
	"go-example/chapter004/main001/trans"
)

var twoPi = 2 * trans.Pi

func main() {
	fmt.Printf("2 * Pi = %g\n", twoPi) // 2*Pi = 6.283185307179586
}
