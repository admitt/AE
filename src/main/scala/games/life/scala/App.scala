package games.life.scala

import Thread.sleep
import io.Source.fromFile

object App {
  def main(args: Array[String]) {
    if (args.length != 1) {
      println("Please provide life description")
    } else {
      var life = Life(fromFile(args(0)).getLines())
      while (true) {
        println("----------")
        println(life)
        life = life.next
        sleep(500)
      }
    }
  }
}