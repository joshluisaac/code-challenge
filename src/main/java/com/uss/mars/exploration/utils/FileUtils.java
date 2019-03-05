package com.uss.mars.exploration.utils;

import com.uss.mars.exploration.Command;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A utility providing file helper methods
 */
public class FileUtils {

    /**
     * Gets a File object based on its path relative to the classpath
     *
     * @param path the path of the file relative to the classpath
     * @return A file handler
     */
    public File getFileFromClasspath(final String path){
        //use the current classloader
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }

    /**
     * Retrieves the contents of a file as a {@link List}
     *
     * @param f the file which is to be read.
     * @return returns a list of strings which is basically the content of the file read.
     * @throws IOException something goes wrong while reading the file
     */
    public static List<String> readFile(final File f) throws IOException {
        try (BufferedReader bufReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(f)))) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = bufReader.readLine()) != null) {
                list.add(line);
            }
            return list;
        }
    }

    //

    /**
     * Will parse, transform and map the commandline raw string input into a {@link List} of {@link Command} objects
     *
     * @param contents the list of raw string to be transformed.
     * @return returns a list of strings which is basically the content of the file read.
     * @throws IllegalAccessException if an illegal command was specified.
     */
    public static List<Command> commandMapper(List<String> contents) throws IllegalAccessException{
        List<Command> commands = new ArrayList<>();
        if (!contents.isEmpty()) {
            for (String line : contents){
                line = line.trim();
                if(line.startsWith("PLACE") || line.startsWith("BLOCK") || line.startsWith("EXPLORE")) {
                    String[] rowCols = line.split("\\s++");
                    String commandName = rowCols[0];
                    String[] points = rowCols[1].split(",");
                    commands.add(new Command(commandName,Integer.parseInt(points[0]),Integer.parseInt(points[1])));
                } else if (line.startsWith("REPORT")) {
                    commands.add(new Command(line));
                } else {
                    throw new IllegalAccessException("The command specified is invalid. Your command must be one of PLACE,BLOCK,EXPLORE or REPORT");
                }
            }
        }
        return commands;
    }


}
