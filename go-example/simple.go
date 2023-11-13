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
	user, _ := os.UserHomeDir()
	fmt.Println(user)
	host, _ := os.Hostname()
	fmt.Println(host)
	fmt.Println(os.Getenv("path"))
}
