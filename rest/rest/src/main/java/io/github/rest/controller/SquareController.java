package io.github.rest.controller;

import io.github.rest.entity.MapTest;
import io.github.rest.entity.Vehicle;
import io.github.rest.service.FileUtilsService;
import io.github.rest.service.SquareService;
import io.swagger.annotations.ApiOperation;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rest")
public class SquareController {

    @Autowired
    SquareService squareService;

    @Autowired
    ResourceLoader resourceLoader;


    @GetMapping("/square/{no}")
    public List<List<Integer>> squareIt(@PathVariable("no") Integer no) {
        return squareService.getSquare(no);
    }

    @GetMapping("/jktest")
    public String justDoIt() {
        try {
            return squareService.doIt();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("/jktest2")
    public List<Vehicle> justDoItAgain() {
        return squareService.doItAgain();
    }

    @GetMapping(value = "/get_zip", produces = "application/zip")
    public ResponseEntity<byte[]> getImageWithMediaType() throws IOException {
        return squareService.checkZip();
    }

    @PostMapping(value = "/csv_upload")
    public ResponseEntity<List<List<String>>> uploadCvlDocsInSftp(@RequestParam("file") MultipartFile file) throws IOException {
        return squareService.readIt(file);
    }

    @PostMapping(value = "/xls_upload")
    public ResponseEntity<byte[]> uploadxlsDocs(@RequestPart("file") MultipartFile file) throws IOException {
        return squareService.parseExcelAndDisplay(file);
    }

    @PostMapping(value = "/map_collect_test")
    public ResponseEntity<MapTest> mapTest() {
        return squareService.mapTest();
    }

    @PostMapping(value = "/add_test")
    public ResponseEntity<List<String>> addrTest(@RequestParam("addr1") final String addr1, @RequestParam("addr2") final String addr2,
            @RequestParam("addr3") final String addr3) {
        return squareService.parseAddress(addr1, addr2, addr3);
    }

    @PostMapping(value = "/xls_getHead")
    public List<Integer> randomEvent(@RequestPart("file") MultipartFile file) throws IOException {
        return squareService.getHeaderCol(file);
    }
}
