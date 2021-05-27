package io.github.rest;

import io.github.rest.entity.CompletePanStatusXmlResponse;
import io.github.rest.entity.CvlPanStatusResponse;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class RestApplicationTests {

	private static final String PAN_REGEX = "^[A-Z]{5}[0-9]{4}[A-Z]$";

	@Test
	void contextLoads() throws UnsupportedEncodingException {
		Pattern pattern = Pattern.compile(PAN_REGEX);
		long start = System.nanoTime();
		Matcher matcher = pattern.matcher("BEXPJ1151H");
		boolean matchFound = matcher.find();
		long finish = System.nanoTime();
		long timeElapsed = finish - start;
		System.out.println("Time Elapsed: "+ timeElapsed);
		if(matchFound) {
			System.out.println("Match found");
		} else {
			System.out.println("Match not found");
		}
	}

	@Test
	void ldTest() {
		LocalDateTime localDate = LocalDateTime.of(2021, 12, 12, 1, 1, 1);
		ZoneId zone = ZoneId.of("Asia/Kolkata");
		ZoneOffset zoneOffSet = zone.getRules().getOffset(localDate);
		Instant instant = localDate.atZone(zoneOffSet).toInstant();
		System.out.println(instant.getEpochSecond());
	}

	@Test
	void xmlTest() throws JAXBException {
		String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?> <APP_RES_ROOT> <APP_PAN_INQ> <APP_PAN_NO>BONPD2820P</APP_PAN_NO> <APP_NAME>Aneelkumar Dudekula</APP_NAME> <APP_STATUS>303</APP_STATUS> <APP_STATUSDT>04-02-2020 19:43:44</APP_STATUSDT> <APP_ENTRYDT>27-01-2020 20:14:01</APP_ENTRYDT> <APP_MODDT> </APP_MODDT> <APP_STATUS_DELTA>24</APP_STATUS_DELTA> <APP_UPDT_STATUS>305</APP_UPDT_STATUS> <APP_HOLD_DEACTIVE_RMKS>DOOR NUMBER MISMATCH BETWEEN ADDRESS PROOF AND APPLICATION</APP_HOLD_DEACTIVE_RMKS> <APP_UPDT_RMKS> </APP_UPDT_RMKS> <APP_KYC_MODE> </APP_KYC_MODE> <APP_IPV_FLAG>N</APP_IPV_FLAG> <APP_UBO_FLAG> </APP_UBO_FLAG> </APP_PAN_INQ> <APP_PAN_SUMM> <BATCH_ID> </BATCH_ID> <APP_RESPONSE_DATE>10-02-2020 12:03:52</APP_RESPONSE_DATE> <APP_TOTAL_REC>1</APP_TOTAL_REC> </APP_PAN_SUMM> </APP_RES_ROOT>";
		CompletePanStatusXmlResponse re = xmlToPanStatusResponse(xmlString);
		System.out.println(re.getPanEnquiry().getPan());
		re.getPanEnquiry().setPan("Encrypted Pan");
		System.out.println(xmlString);
		System.out.println(objToXmlString(re));

	}
	public  CompletePanStatusXmlResponse xmlToPanStatusResponse(String xml) {
		CompletePanStatusXmlResponse response = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(CompletePanStatusXmlResponse.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			response = (CompletePanStatusXmlResponse) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String objToXmlString(final CompletePanStatusXmlResponse cvlPanStatusResponse) throws JAXBException {
		JAXBContext contextObj = JAXBContext.newInstance(CompletePanStatusXmlResponse.class);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		//Print XML String to Console
		StringWriter sw = new StringWriter();
		//Write XML to StringWriter
		marshallerObj.marshal(cvlPanStatusResponse, sw);
		//Verify XML Content
		return sw.toString();
	}
}
