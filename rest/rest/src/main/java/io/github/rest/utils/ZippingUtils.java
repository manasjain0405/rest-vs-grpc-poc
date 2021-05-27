package io.github.rest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class ZippingUtils {

    @Autowired
    ResourceLoader resourceLoader;

    public File createZip(final List<String> srcFileNameList, final String compressedFileName) throws IOException {
        File zipFile = new File(compressedFileName);
        FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
        ZipOutputStream zipOut = new ZipOutputStream(fileOutputStream);
        srcFileNameList.forEach(fileName -> {
            File fileToZip = new File(fileName);
            try {
                FileInputStream fileInputStream = new FileInputStream(fileName);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

//                byte[] bytes = new byte[1024];
//                int length;
//                while ((length = fileInputStream.read(bytes)) >= 0) {
//                    zipOut.write(bytes, 0, length);
//                }
                zipOut.write(Files.readAllBytes(Paths.get(fileName)));
                fileInputStream.close();
            } catch (IOException e) {
                System.out.println("Unable to add file in zip " + fileName);
                e.printStackTrace();
            }
        });
        zipOut.close();
        fileOutputStream.close();
        System.out.println(zipFile.getPath());
        System.out.println(zipFile.getAbsolutePath());
        System.out.println(zipFile.getName());
        System.out.println(zipFile.getCanonicalPath());
        Resource result = resourceLoader.getResource(Paths.get(compressedFileName).toString());
        byte [] res = Files.readAllBytes(Paths.get(compressedFileName));
        return zipFile;
    }


}
