package games.life.ui;

import games.life.Life;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    private Painter painter;

    public GUI(Life life) {
        super("Game of life");
        this.painter = new Painter(life);
        setSize(1000, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        painter.init().draw(g).next();
    }

    private class Painter {
        private int cellWidth, cellHeight, currentWidth, currentHeight, generation = 1;
        private Dimension dimension;
        private Life life;

        private Painter(Life life) {
            this.life = life;
            dimension = life.getDimension();
        }

        private Painter init() {
            currentWidth = cellWidth = getWidth() / dimension.width;
            currentHeight = cellHeight = getHeight() / dimension.height;
            return this;
        }

        private Painter draw(Graphics g) {
            g.setColor(Color.BLUE);
            for (String s : life.getLife()) {
                drawCells(g, s);
            }
            drawGenerationNumber(g);
            return this;
        }

        private void next() {
            life = life.next();
            generation++;
        }

        private void drawCells(Graphics g, String s) {
            for (char c : s.toCharArray()) {
                if (c == '*') {
                    g.fillOval(currentWidth - cellWidth, currentHeight - cellHeight, cellWidth, cellHeight);
                }
                currentWidth += cellWidth;
            }
            currentHeight += cellHeight;
            currentWidth = cellWidth;
        }

        private void drawGenerationNumber(Graphics g) {
            g.setFont(new Font("SansSerif", Font.BOLD, 16));
            g.drawString("Generation: " + generation, 100, 100);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GUI gui = new GUI(new Life(readLife(args[0])));
        while (true) {
            gui.repaint();
            Thread.sleep(100);
        }
    }

    private static String[] readLife(final String fileName) throws IOException {
        return FileUtils.readLines(new File(fileName)).toArray(new String[0]);
    }
}
