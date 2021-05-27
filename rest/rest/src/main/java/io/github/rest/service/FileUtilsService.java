package io.github.rest.service;

import io.github.rest.utils.GroupingCollector;
import io.github.rest.utils.ZippingUtils;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUtilsService {

    @Autowired
    private ZippingUtils zippingUtils;

    public ResponseEntity<byte[]> splitCsv(final MultipartFile file, final Integer chunkSize) throws IOException {

        //Read Csv Data
        InputStream csvInputStream = file.getInputStream();
        CSVReader reader = new CSVReader(new InputStreamReader(csvInputStream));
        //Break Csv into chunks ok 10k entries
        List<List<String[]>> chunkedData = reader.readAll().stream()
                //.filter(this::validations)
                .collect(new GroupingCollector<>(chunkSize));
        //Create a zip file
        File zipFile = new File("chunkedCsv.zip");
        FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
        ZipOutputStream zipFileStream = new ZipOutputStream(fileOutputStream);
        //Create individual csv files and add to zip
        AtomicInteger fileIndex = new AtomicInteger(0);
        chunkedData.forEach(csvData -> {
            try {
                final String csvChunkFileName = String.format("csvChunk_%d.csv", fileIndex.getAndIncrement());
                final File csvChunkFile = new File(csvChunkFileName);
                FileWriter out = new FileWriter(csvChunkFile);
                try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
                    csvData.forEach( csvRow -> {
                        try {
                            printer.printRecord(Arrays.asList(csvRow));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                addFileToZip(csvChunkFile, zipFileStream);
                csvChunkFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        zipFileStream.close();
        fileOutputStream.close();
        ResponseEntity<byte[]> result = ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/zip"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipFile.getName() + "\"")
                .body(Files.readAllBytes(zipFile.toPath()));
        //zipFile.delete();
        return result;
    }

    private boolean validations(final String[] csvEntry){
        return Objects.nonNull(csvEntry[0]) && Objects.nonNull(csvEntry[1]) &&
                csvEntry[0].length() == 16 && !csvEntry[1].equals("");
    }

    private void addFileToZip(final File fileToZip, final ZipOutputStream zipFileStream) {

        try {
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipFileStream.putNextEntry(zipEntry);
            zipFileStream.write(Files.readAllBytes(fileToZip.toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
