package com.adamclmns.mde;

import com.google.common.io.ByteStreams;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This Class finds the
 */
public class MizExtractor {
    private static final int BUFFER_SIZE = 4096;

    /**
     * Unzip byte [ ].
     *
     * @param zipFilePath   the path to the .miz file being scanned
     * @return the byte [ ] representing the 'mission' file
     * @throws IOException the io exception
     */
// Finds the 'mission' file inside the .miz (zip) archive. Returns the bytes of data inside.
    public static byte[] getMissionFromMiz(String zipFilePath) throws IOException {
        byte [] data = null;

        try(ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
//        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry entry = zipIn.getNextEntry();
            // iterates over entries in the zip file
            while (entry != null) {

                if (!entry.isDirectory()) {
                    // if the entry is a file, Examine closer, is it 'mission'
                    //extractFile(zipIn, filePath);
                    if (entry.getName().equalsIgnoreCase("MISSION")) {
                        data = ByteStreams.toByteArray(zipIn);
                    }
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
        return data;
    }

    /**
     * Read mission file as byte[] and return Input Stream Reader
     *
     * @param data the byte[] representing the 'mission' file
     * @return the input stream reader
     */
    public static InputStreamReader readMissionFile(byte[] data){
        return new InputStreamReader(new ByteArrayInputStream(data));
    }

    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
}