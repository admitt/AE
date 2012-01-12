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
        String[] newLife = new String[life.length];
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

    private int getNumberOfCells(final int row, final int column) {
        if (row < 0 || row >= life.length) {
            return 0;
        }
        if (column < 0 || column >= life[row].length()) {
            return 0;
        }
        return life[row].charAt(column) == '*' ? 1 : 0;
    }
    
    static boolean isAlive(final int numberOfNeighbours, final boolean alive) {
        if (numberOfNeighbours < 2 || numberOfNeighbours > 3) {
            return false;
        }
        if (numberOfNeighbours == 3) {
            return true;
        }
        return alive;
    }
}
