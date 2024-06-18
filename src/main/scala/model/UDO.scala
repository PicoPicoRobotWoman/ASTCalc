package model

case class UDO(symbol: String,
							 precedence: Byte,
							 zeroElem: Option[Double] = None,
							 fun: (Double, Double) => Double)
