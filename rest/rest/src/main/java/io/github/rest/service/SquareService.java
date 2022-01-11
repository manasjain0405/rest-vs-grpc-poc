package io.github.rest.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.rest.entity.Bus;
import io.github.rest.entity.Car;
import io.github.rest.entity.MapTest;
import io.github.rest.entity.Pojo;
import io.github.rest.entity.Truck;
import io.github.rest.entity.Vehicle;
import io.github.rest.utils.GroupingCollector;
import io.github.rest.utils.ZippingUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SquareService {

    private final RestTemplate restTemplate;
    private final ZippingUtils zippingUtils;

    private int panColumnNumber = 3;
    private int ksmStatusColumnNumber = 4;
    private int kycNameColumnNumber = 5;
    private int entryDateColumnNumber = 6;
    private int kycModificationDateColumnNumber = 7;
    private int imageStatusColumnNumber = 8;
    private int imageSourceColumnNumber = 9;

    public SquareService(RestTemplateBuilder restTemplateBuilder, ZippingUtils zippingUtils) {
        this.restTemplate = restTemplateBuilder.build();
        this.zippingUtils = zippingUtils;
    }

    public List<List<Integer>> getSquare(int no) {
        return IntStream.range(0, no).boxed().collect(new GroupingCollector<>(5));


    }

    public String doIt() throws JsonProcessingException {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/jktest/test", String.class);

        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Vehicle> vehicles = mapper.readValue(responseEntity.getBody(), mapper.getTypeFactory().constructCollectionType(
                List.class, Vehicle.class));
        vehicles.forEach(this::process);
        return responseEntity.getBody();
    }

    public List<Vehicle> doItAgain() {
        ResponseEntity<Pojo> responseEntity = this.restTemplate.getForEntity("http://localhost:8080/jktest/test2", Pojo.class);
        Objects.requireNonNull(responseEntity.getBody()).getVehicleList().forEach(this::process);
        return Objects.requireNonNull(responseEntity.getBody()).getVehicleList();
    }

    public ResponseEntity<byte[]> checkZip() throws IOException {
//        final String fl = "Compressed.zip";
//        File myObj = new File("filename1.txt");
//        myObj.createNewFile();
//        FileWriter fw = new FileWriter(myObj.getAbsolutePath());
//        fw.write("This is test file 1");
//        fw.close();
//        File myObj2 = new File("filename2.txt");
//        System.out.println(myObj2.getAbsolutePath());
//        myObj2.createNewFile();
//        FileWriter fw2 = new FileWriter(myObj2.getAbsolutePath());
//        fw2.write("This is test file 2");
//        fw2.close();
        List<String> toWrite = Arrays.asList("Line 1 - 1", "line 2 - 2", "line3-3");
        File f1 = new File("csvFile1.csv");
        FileWriter out1 = new FileWriter(f1);
        try (CSVPrinter printer1 = new CSVPrinter(out1, CSVFormat.DEFAULT)) {
            printer1.printRecord(toWrite);
        }

        File f2 = new File("csvFile2.csv");
        FileWriter out2 = new FileWriter(f2);
        try (CSVPrinter printer2 = new CSVPrinter(out2, CSVFormat.DEFAULT)) {
            toWrite.forEach(
                    userId -> {
                        try {
                            printer2.printRecord(userId);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        List<String> lt = new ArrayList<>();
        lt.add(f1.getName());
        lt.add(f2.getName());
        File res = zippingUtils.createZip(lt, "ompressed.zip");
        f1.delete();
        f2.delete();
        final byte[] fileBts = Files.readAllBytes(Paths.get(res.getName()));
        ResponseEntity<byte[]> ret = ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/zip"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + res.getName() + "\"")
                .body(fileBts);
        res.delete();
        return ret;
    }

    private void process(Vehicle vehicle) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(vehicle));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        switch (vehicle.getType()) {
            case "Car":
                Car car = (Car) vehicle;
                System.out.println("Car Name: " + car.getModel());
                break;
            case "Truck":
                Truck truck = (Truck) vehicle;
                System.out.println("Truck Name: " + truck.getName());
                break;
            case "Bus":
                Bus bus = (Bus) vehicle;
                System.out.println("Bus Name :" + bus.getName());
                break;
            default:
                System.out.println("Nota");
                break;
        }
    }

    public ResponseEntity<List<List<String>>> readIt(final MultipartFile file) throws IOException {

        InputStream csvInputStream = file.getInputStream();
        CSVReader reader = new CSVReader(new InputStreamReader(csvInputStream));
        List<List<String>> stst = reader.readAll().stream()
                .map(Arrays::asList)
                .collect(Collectors.toList());
        return ResponseEntity.ok(stst);

    }

    public ResponseEntity<byte[]> parseExcelAndDisplay(final MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        boolean firstIndex = true;
        int submitted = 0;
        int notSubmitted = 0;
        Sheet accepted = workbook.createSheet("Accepted");
        Sheet rejected = workbook.createSheet("Rejected");
        accepted.setColumnWidth(0, 6000);
        accepted.setColumnWidth(1, 6000);
        rejected.setColumnWidth(0, 6000);
        rejected.setColumnWidth(1, 6000);
        Row header = accepted.createRow(0);
        Row rejHeader = rejected.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Status");
        //headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Pan");
        //headerCell.setCellStyle(headerStyle);

        headerCell = rejHeader.createCell(0);
        headerCell.setCellValue("Status");
        //headerCell.setCellStyle(headerStyle);

        headerCell = rejHeader.createCell(1);
        headerCell.setCellValue("Pan");

        final List<Integer> result = new ArrayList<>();
        final AtomicInteger i = new AtomicInteger(1);
        int j = 1;
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        for (Row row : sheet) {
            if (firstIndex) {
                firstIndex = false;
            } else {
                if ("IMAGES RECEIVED".equals(row.getCell(8).getStringCellValue())) {
                    Row writeRow = accepted.createRow(i.getAndIncrement());
                    Cell cell = writeRow.createCell(0);
                    cell.setCellValue(row.getCell(8).getStringCellValue());
                    //cell.setCellStyle(style);
                    cell = writeRow.createCell(1);
                    cell.setCellValue(row.getCell(3).getStringCellValue());
                    //cell.setCellStyle(style);
                    submitted++;
                } else {
                    Row writeRow = rejected.createRow(j++);
                    Cell cell = writeRow.createCell(0);
                    cell.setCellValue(row.getCell(8).getStringCellValue());
                    //cell.setCellStyle(style);
                    cell = writeRow.createCell(1);
                    cell.setCellValue(row.getCell(3).getStringCellValue());
                    notSubmitted++;
                }
            }
        }
        result.add(submitted);
        result.add(notSubmitted);
        String filename = String.format("CvlSftpRecon_%s.xlsx", "test");
        File workbookFile = new File(filename);
        FileOutputStream fileOutputStream = new FileOutputStream(workbookFile);
        workbook.write(fileOutputStream);
        ResponseEntity<byte[]> res = ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + workbookFile.getName() + "\"")
                .body(FileUtils.readFileToByteArray(workbookFile));
        workbookFile.delete();
        return res;
    }

    public ResponseEntity<MapTest> mapTest() {
        List<MapTest> lt = new ArrayList<>();
        lt.add(new MapTest("KEY", "VAL1"));
        lt.add(new MapTest("KEY", "VAL2"));
        lt.add(new MapTest("KEY", "VAL3"));
        lt.add(new MapTest("KEY", "VAL4"));
        lt.add(new MapTest("KEY", "VAL5"));
        return ResponseEntity.ok(lt.stream().collect(Collectors.groupingBy(MapTest::getKey)).get("KEY").get(0));
    }

    public ResponseEntity<List<String>> parseAddress(String addr1, String addr2, String addr3) {
        String fullAddress = addr1 + " " + addr2 + " " + addr3;
        String fullAddressParse = parse(fullAddress);
        System.out.println(fullAddressParse);
        String[] response = trimWholeWordsLessThan(fullAddressParse);
        //response = trimWholeWordsLessThan(fullAddress.replace(',', ' '));
        System.out.println(Arrays.toString(response));
        if (response[0] == null || StringUtils.isEmpty(response[0]) || response[0].length() <= 5) {
            //special handling incase cvl sends no space comma seperated data
            fullAddressParse = parse(fullAddress.replace(',', ' '));
            response = trimWholeWordsLessThan(fullAddressParse);
        }
        System.out.println(Arrays.toString(response));

        Pattern zipPattern = Pattern.compile("(\\d{6})");
        for (int i = 0; i < 3; i++) {
            if (!StringUtils.isEmpty(response[i])) {
                Matcher zipMatcher = zipPattern.matcher(response[i]);
                if (zipMatcher.find()) {
                    String pincode = zipMatcher.group(1);
                    response[i] = response[i].replace(pincode, Strings.EMPTY);
                    if (" ".equals(response[i]) || Strings.EMPTY.equals(response[i])) {
                        response[i] = null;
                    }
                }
            }
        }

        List<String> list = new ArrayList<>();
        list.add(Objects.nonNull(response[0]) ? response[0] : "");
        list.add(Objects.nonNull(response[1]) ? response[1] : ".");
        list.add(Objects.nonNull(response[2]) ? response[2] : ".");
        return ResponseEntity.ok(list);
    }


    public static String[] trimWholeWordsLessThan(String item) {
        item = " " + item + " ";
        String[] response = new String[3];
        char[] list = item.toCharArray();
        int index1 = -1;
        int index2 = -1;
        int index3 = -1;
        for (int i = 0; i < list.length; i++) {
            if (i < 32 && list[i] == ' ') {
                index1 = i;
            } else if (i > index1 && list[i] == ' ' && i < 32 + index1) {
                index2 = i;
            } else if (i > index2 && list[i] == ' ' && i < 32 + index2) {
                index3 = i;
            }
            System.out.println(" i1 " + index1 + " i2 " + index2 + " i3 " + index3);
        }
        response[0] = item.substring(0, index1);
        if (index2 != -1) {
            response[1] = item.substring(index1, index2);
        }
        if (index3 != -1) {
            response[2] = item.substring(index2, index3);
        }
        //log.info("Returning response: {} and {} and {}", response[0], response[1], response[2]);
        return response;
    }

    public static String parse(String message) {
        String specialCharacters = "^%*$:+|~!=/\\\\_.\\-'@(),:+$\"<>#[]\n";
        if (message == null) {
            return message;
        }
        for (Character c : specialCharacters.toCharArray()) {
            //System.out.println(c);
            message = message.replace(c.toString(), "");
            //System.out.println(message);
        }
        message = message.replace("null", "");
        message = message.trim();
        //log.info("returning message: {}", message);
        return message.toUpperCase();
    }



    private void fetchColumnNumbers(final Row headingRow) {

        IntStream.range(0, 11).boxed().forEach(colNo -> {
            try {
                Cell headingCell = headingRow.getCell(colNo);
                if (Objects.nonNull(headingCell)) {
                    String heading = headingCell.getStringCellValue().toString();
                    System.out.println(heading);
                    if ("KYC_PANNO".equals(heading)) {
                        this.panColumnNumber = colNo;
                    }  if ("IMAGES STATUS".equals(heading)) {
                        this.imageStatusColumnNumber = colNo;
                        System.out.println(colNo);
                    }  if ("SOURCE".equals(heading)) {
                        this.imageSourceColumnNumber = colNo;
                    }  if ("KSM_STATUS".equals(heading)) {
                        this.ksmStatusColumnNumber = colNo;
                    }  if ("KYC_APPNAME".equals(heading)) {
                        this.kycNameColumnNumber = colNo;
                    }  if ("ENTRYDT".equals(heading)) {
                        this.entryDateColumnNumber = colNo;
                    }  if ("KYC_MODIFYDT".equals(heading)) {
                        this.kycModificationDateColumnNumber = colNo;
                    }
                }
            } catch (Exception e) {
                System.out.println("Unable to set column no for CVL SFTP Recon File");
                e.printStackTrace();
            }
        });
    }

    public List<Integer> getHeaderCol(final MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        if (rowIterator.hasNext()) {
            Row headingRow = rowIterator.next();
            //Will populate column numbers according to the heading row
            fetchColumnNumbers(headingRow);
        }
        return Arrays.asList(panColumnNumber, imageStatusColumnNumber);
    }

    @CircuitBreaker(name = "testConfig")
    public void intentionalExceptionGenerator(final boolean generateException) {

        if(generateException) {
            throw new RuntimeException("This is an intentional exception!");
        }
    }

}
