package trans

import (
	"fmt"
	"math"
)

var Pi float64

func init() {
	Pi = 4 * math.Atan(1) // init() 函数计算圆周率
	fmt.Println("Pi =", Pi)
}
