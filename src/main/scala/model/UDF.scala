package model

case class UDF(symbol: String,
							fun: List[Double] => Double)
