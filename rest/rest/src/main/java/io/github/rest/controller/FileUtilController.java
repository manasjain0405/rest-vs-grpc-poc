package io.github.rest.controller;

import io.github.rest.service.FileUtilsService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileUtils")
public class FileUtilController {

    @Autowired
    FileUtilsService fileUtilsService;

    @PostMapping(value = "/split_csv")
    public ResponseEntity<byte[]> splitCsv(@RequestParam("csvFile") MultipartFile file, @RequestParam("csvChunkSize") Integer csvChunkSize) {
        try {
            return fileUtilsService.splitCsv(file, csvChunkSize);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
