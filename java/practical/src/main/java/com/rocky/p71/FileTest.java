package com.rocky.p71;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File file = new File("d:/java/MyWorkspace");
        String pre = "--";
        printFile(file, pre);
    }

    private static void printFile(File file, String pre) {

        System.out.println(pre + file.getName());

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isFile()){
                    continue;
                }
                printFile(file1, "   " + pre);
            }
        }
    }
}
