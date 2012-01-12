package games.life.scala

class Life private(private val lifeMap: Map[Int, Map[Int, Life#Cell]]) {

  private sealed trait Cell

  def next: Life = {
    new Life(nextGeneration)
  }

  override def toString: String = {
    mapToDescription(lifeMap) mkString "\n"
  }

  private def nextGeneration: Map[Int, Map[Int, Cell]] = {
    lifeMap map nextGenerationRow
  }

  private def nextGenerationRow(rowWithIndex: (Int, Map[Int, Cell])): (Int, Map[Int, Cell]) = {
    val (rowIndex, row) = rowWithIndex
    (rowIndex, row map nextGenerationCell(rowIndex))
  }

  private def nextGenerationCell(rowIndex: Int)(cellWithIndex: (Int, Cell)): (Int, Cell) = {
    val (cellIndex, cell) = cellWithIndex
    (cellIndex, nextGenerationCell(rowIndex, cellIndex, cell))
  }

  private def nextGenerationCell(rowIndex: Int, cellIndex: Int, currentCell: Cell): Cell = {
    numberOfNeighbours(rowIndex, cellIndex) match {
      case 2 => currentCell
      case 3 => Life.LIVE
      case _ => Life.DEAD
    }
  }

  private def numberOfNeighbours(rowIndex: Int, cellIndex: Int): Int = {
    (List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
      map numberOfLiveCells(rowIndex, cellIndex)).sum
  }

  private def numberOfLiveCells(rowIndex: Int, cellIndex: Int)(shift: (Int, Int)): Int = {
    val (rowShift, cellShift) = shift
    lifeMap.get(rowIndex + rowShift) match {
      case None => 0
      case Some(row) =>
        row.get(cellIndex + cellShift) match {
          case None => 0
          case Some(cell) => cell match {
            case LIVE => 1
            case DEAD => 0
          }
        }
    }
  }

  private def mapToDescription(map: Map[Int, Map[Int, Cell]]): Iterable[String] = {
    map.toSeq.sortBy(_._1) map (_._2) map rowToLine
  }

  private def rowToLine(row: Map[Int, Cell]): String = {
    row.toSeq.sortBy(_._1) map (_._2) map cellToChar mkString ""
  }

  private def cellToChar(cell: Cell): Char = {
    cell match {
      case LIVE => '*'
      case DEAD => '.'
      case _ => ' '
    }
  }
}

object Life {

  private case object DEAD extends Life#Cell

  private case object LIVE extends Life#Cell

  def apply(description: Iterator[String]): Life = {
    new Life(textToMap(description.toIterable))
  }

  private def textToMap(description: Iterable[String]): Map[Int, Map[Int, Life#Cell]] = {
    ((0 until description.size) zip (description map lineToRow)).toMap
  }

  private def lineToRow(line: String): Map[Int, Life#Cell] = {
    ((0 until line.length) zip (line map charToCell)).toMap
  }

  private def charToCell(char: Char): Life#Cell = {
    char match {
      case '*' => LIVE
      case '.' => DEAD
      case _ => DEAD
    }
  }
}
