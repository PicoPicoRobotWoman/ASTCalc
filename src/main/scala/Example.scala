import model._

import scala.util.Random

object Example extends App {

	val plus = UDO("+", 1, Option(0d), (left, right) => left + right)
	val minus = UDO("-", 1, Option(0d), (left, right) => left - right)
	val multiply = UDO("*", 2, Option(1d), (left, right) => left * right)
	val division = UDO("/", 2, None, (left, right) => left / right)
	val pow = UDO("^", 3, None, (left, right) => Math.pow(left, right))

	val avg = UDF("avg", params => params.sum / params.size)
	val abs = UDF("abs", params => params.head.abs)
	val cos = UDF("cos", params => Math.cos(params.head))
	val sin = UDF("sin", params => Math.sin(params.head))
	val ln = UDF("ln", params => Math.log(params.head))
	val max = UDF("max", params => params.max)
	val min = UDF("min", params => params.min)
	val ran = UDF("ran", params => Random.nextDouble())

	val pi = UDC("pi", Math.PI)
	val e = UDC("e", Math.E)

	implicit val udos: Set[UDO] = Set(plus, minus, multiply, division, pow)
	implicit val udfs: Set[UDF] = Set(avg, abs, cos, sin, ln, max, min, ran)
	implicit val udcs: Set[UDC] = Set(pi, e)

	import model.AST._
	println("cos(pi/2)^2+sin((pi/2) - (-0))^2".parseAST().calc()) // 1.0
	println("avg(2, 4) * 3".parseAST().calc()) // 9.0
	println("abs(-3.5) + ln(e)".parseAST().calc()) // 4.5
	println("max(1, 3, 2) - min(5, 3, 4)".parseAST().calc()) // -2.0
	println("2^3 + ran()".parseAST().calc()) // 8 + random value between 0 and 1
	println("cos(0) + sin(pi/2)".parseAST().calc()) // 1.0

	/*
				 _____________________
				|  _________________  |
				| |              0. | |
				| |_________________| |
				|  ___ ___ ___   ___  |
				| | 7 | 8 | 9 | | + | |
				| |___|___|___| |___| |
				| | 4 | 5 | 6 | | - | |
				| |___|___|___| |___| |
				| | 1 | 2 | 3 | | x | |
				| |___|___|___| |___| |
				| | . | 0 | = | | / | |
				| |___|___|___| |___| |
				|_____________________|


	 */

}
