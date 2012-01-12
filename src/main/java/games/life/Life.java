package games.life;

public class Life {
    private final String[] life;

    public Life(String[] life) {
        this.life = life;
    }

    @Override
    public String toString() {
        StringBuilder lifeBuilder = new StringBuilder();
        for (final String lineRow : life) {
            if (lifeBuilder.length() != 0) {
                lifeBuilder.append('\n');
            }
            lifeBuilder.append(lineRow);
        }
        return lifeBuilder.toString();
    }

    public Life next() {
        return this;
    }

    int getNumberOfNeighbours(final int row, final int column) {
        int numberOfNeighbours = 0;
        numberOfNeighbours += getNumberOfCells(row - 1, column - 1);
        numberOfNeighbours += getNumberOfCells(row - 1, column);
        numberOfNeighbours += getNumberOfCells(row - 1, column + 1);
        numberOfNeighbours += getNumberOfCells(row, column - 1);
        numberOfNeighbours += getNumberOfCells(row, column + 1);
        numberOfNeighbours += getNumberOfCells(row + 1, column - 1);
        numberOfNeighbours += getNumberOfCells(row + 1, column);
        numberOfNeighbours += getNumberOfCells(row + 1, column + 1);
        return numberOfNeighbours;
    }

    int getNumberOfCells(final int row, final int column) {
        if (row < 0 || row >= life.length) {
            return 0;
        }
        if (column < 0 || column >= life[row].length()) {
            return 0;
        }
        return life[row].charAt(column) == '*' ? 1 : 0;
    }
}
