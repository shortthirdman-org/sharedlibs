package com.shortthirdman.sharedlibs.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FileUtils {

    /**
     * @param sourceFile
     * @param targetFile
     */
    public static void gzipCompress(String sourceFile, String targetFile) {
        try (FileOutputStream fos = new FileOutputStream(targetFile);
             GZIPOutputStream gzos = new GZIPOutputStream(fos);
             FileInputStream fis = new FileInputStream(sourceFile);) {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                gzos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param sourceFile
     * @param targetFile
     */
    public static void gzipDecompress(String sourceFile, String targetFile) {
        try (FileInputStream fis = new FileInputStream(sourceFile);
             GZIPInputStream gzis = new GZIPInputStream(fis);
             FileOutputStream fos = new FileOutputStream(targetFile);) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = gzis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            System.err.println(e.getCause() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param source
     * @param destination
     */
    public static void copyFiles(File source, File destination) {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getCause() + ": " + ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    /**
     * @param source
     * @param destination
     * @throws IOException
     */
    public static void copyFile(String source, String destination) throws IOException {
        try (InputStream is = Files.newInputStream(new File(source).toPath()); OutputStream os = Files.newOutputStream(new File(destination).toPath())) {
            int length;
            byte[] bytes = new byte[1024];
            // copy data from input stream to output stream
            while ((length = is.read(bytes)) != -1) {
                os.write(bytes, 0, length);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param source
     * @param destination
     * @throws IOException
     */
    public static void copyFile(File source, File destination) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(destination).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }

    /**
     * @param dir
     */
    public static void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }

    /**
     * @param dir
     */
    public static void cleanDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
        }
    }
}
