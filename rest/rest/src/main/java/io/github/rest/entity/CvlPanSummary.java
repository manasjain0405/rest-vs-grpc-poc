package io.github.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CvlPanSummary implements Serializable {

    @XmlElement(name = "BATCH_ID")
    private String batchId;

    @XmlElement(name = "APP_RESPONSE_DATE")
    private String responseDate;

    @XmlElement(name = "APP_TOTAL_REC")
    private String totalRec;
}

//<APP_PAN_SUMM>
//    <BATCH_ID>12012021104903</BATCH_ID>
//    <APP_RESPONSE_DATE>12-01-2021 10:49:03</APP_RESPONSE_DATE>
//    <APP_TOTAL_REC>1</APP_TOTAL_REC>
//  </APP_PAN_SUMM>