package games.life.scala

import Thread.sleep

object App {
  def main(agrs: Array[String]) {
    val life = new Life()
    while (true) {
      println(life)
      sleep(500)
    }
  }
}