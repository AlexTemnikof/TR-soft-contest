package com.example.contestapp.tools;

import java.nio.file.Path;

public class FileTools {
    public static String getFileExtension(String filename){
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < filename.length() - 1) {
            return filename.substring(lastDotIndex + 1);
        } else {
            return "";
        }
    }

}
