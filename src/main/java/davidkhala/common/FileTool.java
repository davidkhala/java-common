package davidkhala.common;


import java.io.*;
import java.util.Objects;

public class FileTool {
    public static void write(File targetFile, String content) throws IOException {
        FileOutputStream fop = new FileOutputStream(targetFile);
        byte[] contentInBytes = content.getBytes();

        fop.write(contentInBytes);
        fop.flush();
        fop.close();
    }

    public static boolean overwrite(File targetFile, String content) throws IOException {
        boolean noExist = targetFile.createNewFile();
        if (noExist) {
            return false;
        }

        FileTool.write(targetFile, content);
        return true;
    }

    public static String read(File targetFile) throws IOException {
        FileInputStream is = new FileInputStream(targetFile);
        return Stream.read(is);
    }

    public static boolean deleteDir(File dir) {

        boolean result = true;
        for (File file : Objects.requireNonNull(dir.listFiles())) {

            if (file.isDirectory()) {
                if (!deleteDir(file)) result = false;
            } else if (!file.delete()) result = false;

        }
        return result;
    }

    public static String getExtensionName(String filename) {
        if (filename != null && !filename.isEmpty()) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
}
