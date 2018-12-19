package com.intenthq.challenge


trait JsonEncodeable[T] {
  def toJson(t: T): String
}

object JsonEncodeable {

  implicit def stringToJson: JsonEncodeable[String] = new JsonEncodeable[String] {
    //Why are the quotes opened with an s?
    //Why are there four quotes either side?
    //What does the ${...} do?
    //What does the `replaceAll` do?
    def toJson(t: String): String = s""""${t.replaceAll("\"","\\\\\"")}""""
  }

  implicit def booleanToJson: JsonEncodeable[Boolean] = new JsonEncodeable[Boolean] {
    def toJson(t: Boolean): String = t.toString
  }

  //what's happening here with the implicit parameter? why are we doing this?
  //what are the downsides of doing this generically instead of once for each numeric type?
  implicit def numberToJson[N](implicit num: Numeric[N]): JsonEncodeable[N] = new JsonEncodeable[N] {
    def toJson(t: N): String = num.toDouble(t).toString
  }

  implicit def listToJson[T](implicit enc: JsonEncodeable[T]): JsonEncodeable[List[T]] = new JsonEncodeable[List[T]] {
    def toJson(t: List[T]): String = {
      val jsonElems = t.map(enc.toJson)
      s"[${jsonElems.mkString(", ")}]"
    }
  }

  implicit def mapToJson[T](implicit encString: JsonEncodeable[String], encValue: JsonEncodeable[T]): JsonEncodeable[Map[String,T]] = new JsonEncodeable[Map[String,T]] {
    def toJson(t: Map[String,T]): String = {
      val jsonElemPairs = t.map { case (k,v) => (encString.toJson(k),encValue.toJson(v)) }
      val jsonElems = jsonElemPairs.map { case (k,v) => s"$k: $v"}
      s"{${jsonElems.mkString(", ")}}"
    }
  }

  //hlistToJson??

}


object JsonUtils {

  implicit class ToJson[T](t: T)(implicit enc: JsonEncodeable[T]) {
    def toJson: String = enc.toJson(t)
  }

  def toJson[T:JsonEncodeable](t: T): String = t.toJson

}