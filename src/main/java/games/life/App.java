package games.life;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(final String[] args) throws InterruptedException, IOException {
        if (args.length != 1) {
            System.err.print("Please specify initial life");
            return;
        }

        int generation = 1;
        Life life = new Life(readLife(args[0]));
        while (true) {
            System.out.println("[" + generation + "]");
            System.out.println(life);
            life = life.next();
            generation++;
            Thread.sleep(500);
        }
    }

    private static String[] readLife(final String fileName) throws IOException {
        return FileUtils.readLines(new File(fileName)).toArray(new String[0]);
    }
}
