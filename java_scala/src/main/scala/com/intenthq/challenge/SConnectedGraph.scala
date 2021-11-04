package com.intenthq.challenge

import scala.collection.mutable

case class Node(value: Int, edges: List[Node] = List.empty)

object SConnectedGraph {

  // Find if two nodes in a directed graph are connected.
  // Based on http://www.codewars.com/kata/53897d3187c26d42ac00040d
  // For example:
  // a -+-> b -> c -> e
  //    |
  //    +-> d
  // run(a, a) == true
  // run(a, b) == true
  // run(a, c) == true
  // run(b, d) == false
  def run(source: Node, target: Node): Boolean = {
    if (source == target) {
      return true
    }
    var visited : Set[Node] = Set()
    val q = new mutable.Queue[Node]
    visited+=source
    q.enqueue(source)
    while(q.nonEmpty){
      val currentNode = q.dequeue()
      for (node <- currentNode.edges){
        if(!visited(node)){
          if (node == target){
            return true
          }
          visited+=node
          q.enqueue(node)
        }
      }
    }
    false
  }

}
