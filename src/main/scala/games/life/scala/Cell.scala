package games.life.scala

sealed trait Cell

case object DEAD extends Cell

case object LIVE extends Cell