package games.life.ui;

import games.life.Life;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

    private Life life;
    private int generation = 1;

    public GUI(Life life) {
        super("Game of life");
        this.life = life;
        setSize(1000, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        Dimension dimension = life.getDimension();
        int cellWidth = getWidth() / dimension.width;
        int cellHeight = getHeight() / dimension.height;

        int currentWidth = cellWidth;
        int currentHeight = cellHeight;
        for (String s : life.getLife()) {
            for (char c : s.toCharArray()) {
                if (c == '*') {
                    g.fillOval(currentWidth - cellWidth, currentHeight - cellHeight,  cellWidth, cellHeight);
                }
                currentWidth += cellWidth;
            }
            currentHeight += cellHeight;
            currentWidth = cellWidth;
        }
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.drawString("Generation: " + generation, 100, 100);
        life = life.next();
        generation++;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int generation = 1;
        GUI gui = new GUI(new Life(readLife(args[0])));
        while (true) {
            gui.repaint();
            System.out.println("[" + generation + "]");
            System.out.println(gui.life);
            generation++;
            Thread.sleep(250);
        }
    }

    private static String[] readLife(final String fileName) throws IOException {
        return FileUtils.readLines(new File(fileName)).toArray(new String[0]);
    }
}
