package main

func main() {
	var a = new(int)
	*a = 1
	DoSomething(a)
	println("------------------")
	DoSomething2(1)
}

func DoSomething(a *int) {
	b := a
	println("a=", a)
	println("b=", b)
}

func DoSomething2(a int) {
	b := &a
	println("a=", a)
	println("b=", b)
}
