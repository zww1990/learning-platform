package main

import (
	"fmt"
	"os"
	"runtime"
)

func main() {
	fmt.Println("Java是世界上最强大的编程语言，没有之一。")
	fmt.Println(runtime.Version())
	fmt.Println(runtime.GOOS)
	fmt.Println(os.UserHomeDir())
	fmt.Println(os.Hostname())
	fmt.Println(os.Getenv("path"))
}
