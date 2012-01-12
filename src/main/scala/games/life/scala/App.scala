package games.life.scala

import Thread.sleep
import io.Source.fromFile
import Life.fromText

object App {
  def main(args: Array[String]) {
    if (args.length != 1) {
      println("Please provide life description")
    } else {
      var life = new Life(fromText(fromFile(args(0)).getLines()))
      while (true) {
        println("----------")
        println(life)
        life = life.next
        sleep(500)
      }
    }
  }
}