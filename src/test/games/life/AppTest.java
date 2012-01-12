package games.life;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppTest {
    private static final String[] LIFE1 = {
            ".*",
            "**"
    };
    private static final String LIFE1_TEXT = LIFE1[0] + '\n' + LIFE1[1];

    private static final String[] LIFE2 = {
            "**",
            "**"
    };

    @Test
    public void lifeView() {
        assertThat(new Life(LIFE1).toString(), is(LIFE1_TEXT));
    }

    @Test
    public void nextLife() {
        assertThat(new Life(LIFE1).next(), is(new Life(LIFE2)));
    }

    @Test
    public void numberOfNeighbours() {
        assertThat(new Life(LIFE1).getNumberOfNeighbours(0, 0), is(3));
        assertThat(new Life(LIFE1).getNumberOfNeighbours(0, 1), is(2));
        assertThat(new Life(LIFE1).getNumberOfNeighbours(1, 0), is(2));
        assertThat(new Life(LIFE1).getNumberOfNeighbours(1, 1), is(2));
    }

    @Test
    public void isAlive() {
        assertThat(Life.isAlive(0, true), is(false));
        assertThat(Life.isAlive(0, false), is(false));
        assertThat(Life.isAlive(1, true), is(false));
        assertThat(Life.isAlive(1, false), is(false));
        assertThat(Life.isAlive(2, true), is(true));
        assertThat(Life.isAlive(2, false), is(false));
        assertThat(Life.isAlive(3, true), is(true));
        assertThat(Life.isAlive(3, false), is(true));
        assertThat(Life.isAlive(4, true), is(false));
        assertThat(Life.isAlive(4, false), is(false));
        assertThat(Life.isAlive(8, true), is(false));
        assertThat(Life.isAlive(8, false), is(false));
    }
}
