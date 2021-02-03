package com.company;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtParser extends FileParser {

    public void parseFile(String path) throws FileNotFoundException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int countWords = 0;
        int countDots = 0;
        HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();


        String patternToMatch = "([a-zA-z0-9]+)";
        Pattern pattern =  Pattern.compile(patternToMatch);
        while (line != null) {
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()){
                countWords++;
                String wordGroup = matcher.group();
                {
                    if (wordCountMap.containsKey(wordGroup)) {
                        wordCountMap.put(wordGroup, wordCountMap.get(wordGroup)+1);
                    }
                    else {
                        wordCountMap.put(wordGroup, 1);
                    }
                }
            }
            countDots = countDots + line.length() - line.replace(".", "").length();
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String mostRepeatedWord = null;

        int count = 0;

        Set<Map.Entry<String, Integer>> entrySet = wordCountMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet)
        {
            if(entry.getValue() > count)
            {
                count = entry.getValue();
                mostRepeatedWord = entry.getKey();
            }
        }

        Statistics stats = new Statistics();
        stats.setNoOfWords(countWords);
        stats.setNoOfDots(countDots);
        stats.setMostUsedWord(mostRepeatedWord);
    }
}