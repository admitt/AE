package games.life;

import java.util.Arrays;

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
        for (int row = 0; row < life.length; row++) {
            final StringBuilder rowBuilder = new StringBuilder(life[row].length());
            for (int column = 0; column < life[row].length(); column++) {
                rowBuilder.append(isAlive(getNumberOfNeighbours(row, column), getNumberOfCells(row, column) > 0) ? '*' : '.');
            }
            newLife[row] = rowBuilder.toString();
        }
        return new Life(newLife);
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

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final Life otherLife = (Life) other;
        return Arrays.equals(life, otherLife.life);
    }

    @Override
    public int hashCode() {
        return life != null ? Arrays.hashCode(life) : 0;
    }
}
