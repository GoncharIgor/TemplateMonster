package com.templatemonster.demo.util;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUtils extends BaseUtils {

    private boolean fileEmptyCheck(File file) {
        if (file.length() == 0) {
            LOGGER.info("File is empty");
            return false;
        }
        return true;
    }

    public File createFile(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); //create all previous folders in case they were not created before
        try {
            if (!file.exists()) {
                file.createNewFile();
                return file;
            } else {
                LOGGER.error("Such file " + file.getName() + "is already exists");
            }
        } catch (IOException e) {
            LOGGER.error("Could not create the file");
            e.printStackTrace();
        }
        return null;
    }

    public String getTextFromTxtFile(String filePath) throws IOException {
        String readingResult = "A lane from file to be read";
        InputStream targetStream = new FileInputStream(new File(filePath));
        try {
            readingResult = IOUtils.toString(targetStream, "utf-8");
        } catch (Exception e) {
            LOGGER.error("Could not find the file");
        }
        return readingResult;

    }

    public void writeTextToTxtFile(String filePath, String lane) {
        BufferedWriter writer = null;
        File file = new File(filePath);

        if (!file.exists()) {
            LOGGER.error("File not exists");
            return;
        }

        String previousData = "";
        try {
            previousData = getTextFromTxtFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(previousData);
            writer.write(lane);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                LOGGER.info("File \'" + file.getName() + "\' was updated with new lane: " + lane);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FileUtils fileUtils = new FileUtils();
        fileUtils.writeTextToTxtFile("./src/test/resources/history/allRandomEmailsThatWereRegistered.txt", "11111111");
        String a = fileUtils.getTextFromTxtFile("./src/test/resources/history/allRandomEmailsThatWereRegistered.txt");
        System.out.println(a);
    }
}
