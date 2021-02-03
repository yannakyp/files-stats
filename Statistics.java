package com.company;


public class Statistics {
    public static Integer noOfWords;
    public static Integer noOfDots;
    public static String mostUsedWord;

    public Integer getNoOfWords() {
        return noOfWords;
    }

    public Integer getNoOfDots() {
        return noOfDots;
    }

    public String getMostUsedWord() {
        return mostUsedWord;
    }

    public void setNoOfWords(Integer words) {
        this.noOfWords = words;
    }

    public void setNoOfDots(Integer dots) {
        this.noOfDots = dots;
    }

    public void setMostUsedWord(String word) {
        this.mostUsedWord = word;
    }

}
