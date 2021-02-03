package com.company;

import java.io.FileNotFoundException;

public abstract class FileParser {
    public abstract void parseFile(String path) throws FileNotFoundException;
}