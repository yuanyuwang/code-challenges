package com.intenthq.challenge

object SNiceStrings {

// From http://adventofcode.com/day/5
//  --- Day 5: Doesn't He Have Intern-Elves For This? ---
//
//  Santa needs help figuring out which strings in his text file are naughty or nice.
//
//    A nice string is one with all of the following properties:
//
//    It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
//  It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
//    It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
//    For example:
//
//    ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
//  aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
//    jchzalrnumimnmhp is naughty because it has no double letter.
//    haegwjzuvuyypxyu is naughty because it contains the string xy.
//    dvszwmarrgswjxmb is naughty because it contains only one vowel.
//    How many strings are nice?

  def doubleLetter(s: String): Boolean ={
    if (s.isEmpty){
      return false
    }

    for(i <- 0 until s.length()-1) {
      if (s.charAt(i)==s.charAt(i+1)){
        return true
      }
    }
    false
  }
  def containBadString(s: String): Boolean = {
    val badWordList: List[String] = List("ab", "cd", "pq", "xy")
    for (word <- badWordList){
      if (s.contains(word)){
        return true
      }
    }
    false
  }
  def containThreeVowels(s: String): Boolean = {
    var vowels : Set[Char] = Set('a','e','i','o','u')
    var counter = 0
    for(i <- 0 until s.length()) {
      if (vowels(s.charAt(i)) ){
        counter+=1
        if (counter == 3){
          return true
        }
      }
    }
    false
  }
  def nice(xs: List[String]): Int = {
    xs.map(_.toLowerCase).filterNot(containBadString).filter(doubleLetter).count(containThreeVowels)
  }
}
