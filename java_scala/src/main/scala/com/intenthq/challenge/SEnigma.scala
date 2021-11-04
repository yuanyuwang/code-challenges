package com.intenthq.challenge;

import scala.collection.mutable
case class TryNode(tryNodes: collection.mutable.Map[Int, TryNode]= collection.mutable.Map[Int, TryNode](), var isWord: Boolean = false, var word: Char ='0')


object SEnigma {

  // We have a system to transfer information from one place to another. This system
  // involves transferring only list of digits greater than 0 (1-9). In order to decipher
  // the message encoded in the list you need to have a dictionary that will allow
  // you to do it following a set of rules:
  //    > Sample incoming message: (​1,2,3,7,3,2,3,7,2,3,4,8,9,7,8)
  //    > Sample dictionary (​23->‘N’,234->‘ ’,89->‘H’,78->‘Q’,37 ->‘A’)
  //  - Iterating from left to right, we try to match sublists to entries of the map.
  //    A sublist is a sequence of one or more contiguous entries in the original list,
  //    eg. the sublist (1, 2) would match an entry with key 12, while the sublist (3, 2, 3)
  //    would match an entry with key 323.
  //  - Whenever a sublist matches an entry of the map, it’s replaced by the entry value.
  //    When that happens, the sublist is consumed, meaning that its elements can’t be used
  //    for another match. The elements of the mapping however, can be used as many times as needed.
  //  - If there are two possible sublist matches, starting at the same point, the longest one
  //    has priority, eg 234 would have priority over 23.
  //  - If a digit does not belong to any matching sublist, it’s output as is.
  //
  // Following the above rules, the message would be: “1N73N7 HQ”
  // Check the tests for some other (simpler) examples.
  def intToArray(input: Int): List[Int] ={
    var result = List[Int]()
    var tmp = input
    while (tmp >=10){
      result = result:+(tmp%10)
      tmp/=10
    }
    result = result:+tmp
    result.reverse
  }
  def buildTry(key: Int, value: Char, tryNode: TryNode): TryNode= {
    val keyArray = intToArray(key)
    var cur:  TryNode = tryNode
    for (key<- keyArray){
      if (!cur.tryNodes.contains(key)){
        cur.tryNodes +=( key -> TryNode())
      }
      cur = cur.tryNodes(key)
    }
    cur.isWord = true
    cur.word = value
    tryNode
  }
  def buildTries(map: Map[Int, Char], tryNode: TryNode): TryNode= {
    for ((key, value) <- map) {
      buildTry(key, value, tryNode)
    }
    tryNode
  }
  def deciphe(map: Map[Int, Char])(message: List[Int]): String = {
    var tryNode = TryNode()
    buildTries(map, tryNode)
    var result: String = ""
    var i, wordEndPos, curPos: Int =0
    var find: Boolean = false
    var word: Char ='0'
    var cur: TryNode =  tryNode
    while(i<message.length){
      curPos = i
      cur = tryNode
      find = false
      if(cur.tryNodes.contains(message(i))){
        while(curPos < message.length && cur.tryNodes.contains(message(curPos))){
          cur = cur.tryNodes(message(curPos))
          if (cur.isWord){
            find = true
            wordEndPos = curPos
            word = cur.word
          }
          curPos+=1
        }
      }
      if (find){
        result+=word
        i= wordEndPos
      }else{
        result+=message(i)
      }
      i+=1
    }
    result
  }

}