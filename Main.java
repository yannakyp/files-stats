package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("You need to specify a path!");
            return;
        }

        final String inputPath = args[0];
        System.out.println("Listening to directory: "+inputPath);

        FileSystem fs = FileSystems.getDefault();
        WatchService ws = null;
        try {
            ws = fs.newWatchService();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path pTemp = Paths.get(inputPath);
        try {
            pTemp.register(ws, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            WatchKey k = null;
            if (ws != null) {
                try {
                    k = ws.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (WatchEvent<?> e : k.pollEvents())
            {
                Object c = e.context();
                System.out.printf("%s %s\n", e.kind(), c);
                // DO PARSING STUFF HERE

                if(c.toString().endsWith(".txt") && (
                        e.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY) ||
                                (e.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)))) {
                    System.out.println("Txt parsing in progress: ");
                    FileParser fileParser = new TxtParser();
                    try {
                        fileParser.parseFile(inputPath + "/" + c.toString());

                        // Statistics
                        System.out.println("--- File Statistics --- ");
                        System.out.println("Number of words: " + Statistics.noOfWords);
                        System.out.println("Number of dots: "+ Statistics.noOfDots);
                        System.out.println("Most repeated word: "+ Statistics.mostUsedWord);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
            k.reset();
        }
    }
}
