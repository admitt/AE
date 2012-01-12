package games.life;

import org.junit.Ignore;
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
    @Ignore
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
}
