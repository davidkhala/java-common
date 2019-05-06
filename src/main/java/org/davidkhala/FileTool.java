package org.davidkhala;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;

import java.io.*;
import java.net.URI;


/**
 * Created by davidliu on 9/29/2016.
 */

public class FileTool {
    public static void write(File targetFile, String content) throws IOException {
        targetFile.createNewFile();
        FileOutputStream fop = new FileOutputStream(targetFile);


        // get the content in bytes
        byte[] contentInBytes = content.getBytes();

        fop.write(contentInBytes);
        fop.flush();
        fop.close();
    }

    public static String read(File targetFile) throws IOException {
        FileInputStream is = new FileInputStream(targetFile);
        return read(is);
    }

    public static String read(InputStream is) throws IOException {
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer);
    }

    public static int copyDir(File from, File to) throws IOException {
        Preconditions.checkArgument(from.isDirectory(),"source file should be directory:"+from.getPath());
        Preconditions.checkArgument(to.isDirectory(),"target file should be directory:"+to.getPath());
        File[] files = from.listFiles();
        if(files==null)return -1;
        for(File file: files){
            File targetFile = new File(to,file.getName());
            if(file.isDirectory()){
                if(targetFile.mkdir()){
                    copyDir(file,targetFile);
                }
            }else {
                Files.copy(file,targetFile);
            }
        }
        return files.length;
    }

    public static boolean deleteDir(File dir) {

        boolean result = true;
        for (File file : dir.listFiles()
                ) {

            if (file.isDirectory()) {
                if (!deleteDir(file)) result = false;
            } else if (!file.delete()) result = false;

        }
        return result;
    }
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
    public static File fromURI(URI uri){
        return new File(uri);
    }
}
