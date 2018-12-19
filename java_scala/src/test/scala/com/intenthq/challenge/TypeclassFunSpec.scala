package com.intenthq.challenge

import org.specs2.mutable.Specification

import JsonUtils._

class TypeclassFunSpec extends Specification {

  "toJson" >> {

    "works on a simple string" >> {
      "abc".toJson must_== "\"abc\""
    }

    "works on a string with quotes" >> {
      "a\"c".toJson must_== "\"a\\\"c\""
    }

    "works on numbers" >> {
      1.toJson must_== "1.0"
      1.0.toJson must_== "1.0"
      1L.toJson must_== "1.0"
    }

    "works on booleans" >> {
      true.toJson must_== "true"
      false.toJson must_== "false"
    }

    "works on homogenous lists" >> {
      List(1, 2, 3).toJson must_== "[1.0, 2.0, 3.0]"
    }

    //why doesn't it work on heterogenous lists, even though these are valid JSON, e.g. List(1, "1", true)?

    "works on heterogenous maps" >> {
      Map("a" -> List(1,2), "b" -> List(3,4)).toJson must_== "{\"a\": [1.0, 2.0], \"b\": [3.0, 4.0]}"
    }

  }

}
