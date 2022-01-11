package io.github.rest.controller;

import io.github.rest.entity.MapTest;
import io.github.rest.entity.Vehicle;
import io.github.rest.service.SquareService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/r4jTesting")
    public void r4jTesting(@RequestParam("throwException") boolean throwException) {
        try {
            squareService.intentionalExceptionGenerator(throwException);
        } catch (Exception e) {

        }

    }
}
