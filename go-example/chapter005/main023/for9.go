package main

func main() {
	for i := 1; i <= 9; i++ {
		for j := 1; j <= i; j++ {
			print(j, "*", i, "=", j*i, "\t")
		}
		println()
	}
}
