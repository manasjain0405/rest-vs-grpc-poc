package io.github.rest.entity;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "APP_RES_ROOT")
@XmlType
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CvlPanStatusResponse implements Serializable {

    @XmlElement(name = "APP_PAN_INQ")
    private CvlPanEnquiry panEnquiry;

    /*
  <APP_RES_ROOT>
    <APP_PAN_INQ>
        <APP_PAN_NO>ATGPG2654K</APP_PAN_NO>
        <APP_NAME>VINOTH  MARIMUTHU</APP_NAME>
        <APP_STATUS>002</APP_STATUS>
        <APP_STATUSDT>14-01-2017 11:53:01</APP_STATUSDT>
        <APP_ENTRYDT>02-01-2012 18:22:03</APP_ENTRYDT>
        <APP_MODDT>27-01-2017 15:55:14</APP_MODDT>
        <APP_STATUS_DELTA>
        </APP_STATUS_DELTA>
        <APP_UPDT_STATUS>001</APP_UPDT_STATUS>
        <APP_HOLD_DEACTIVE_RMKS>
        </APP_HOLD_DEACTIVE_RMKS>
        <APP_UPDT_RMKS>
        </APP_UPDT_RMKS>
        <APP_KYC_MODE>1</APP_KYC_MODE>
        <APP_IPV_FLAG>Y</APP_IPV_FLAG>
        <ERROR>
          <ERROR_CODE>WEBERR-999</ERROR_CODE>
          <ERROR_MSG>INVALID PAN NO FORMAT</ERROR_MSG>
        </ERROR>
    </APP_PAN_INQ>

     <APP_PAN_SUMM>
        <BATCH_ID>30032018134408</BATCH_ID>
        <APP_RESPONSE_DATE>30-03-2018 13:44:08</APP_RESPONSE_DATE>
        <APP_TOTAL_REC>1</APP_TOTAL_REC>
      </APP_PAN_SUMM>
   </APP_RES_ROOT>
     */
}
