package com.intenthq.challenge

import scala.collection.mutable.Set

case class Node(value: Int, edges: List[Node] = List.empty) {

  override def equals(obj: Any) = obj match {
    case Node(v,_) => value == v
    case _ => false
  }

}

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
  def run(source: Node, target: Node): Boolean =
    if (source == target) true
    else {
      source.edges.exists(src => run(src,target))
    }

  def runCyclic(source: Node, target: Node, visited: Set[Node] = Set.empty): Boolean =
    //why do we use a set here?
    if (visited.contains(source)) false
    else if (source == target) true
    else {
      visited += source //what would happen if an immutable set was used?
      source.edges.exists(runCyclic(_,target,visited))
    }

}
