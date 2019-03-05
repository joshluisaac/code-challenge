package com.uss.mars.exploration.utils;

import java.io.*;
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
    public File getFileFromClasspath(String path){
        //use the current classloader
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }

    /**
     * Retrieves the contents of a file as a {@link List<String>}
     *
     * @return returns a list of strings which is basically the content of the file read.
     * @throws IOException something goes wrong while reading the file
     */
    public List<String> readFile(final File f) throws IOException {
        try (BufferedReader bufReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(f)));) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = bufReader.readLine()) != null) {
                list.add(line);
            }
            return list;
        }
    }


}
