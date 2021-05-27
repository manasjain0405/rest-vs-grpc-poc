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
public class CvlPanEnquiry implements Serializable {

    @XmlElement(name = "APP_PAN_NO")
    private String pan;

    @XmlElement(name = "APP_NAME")
    private String name;

    @XmlElement(name = "APP_STATUS")
    private String status;

    @XmlElement(name = "APP_STATUSDT")
    private String statusDateStr;

    @XmlElement(name = "APP_ENTRYDT")
    private String entryDateStr;

    @XmlElement(name = "APP_MODDT")
    private String modifiyDateStr;

    @XmlElement(name = "APP_UPDT_STATUS")
    private String updateStatus;

    @XmlElement(name = "APP_UPDT_RMKS")
    private String updateRemarks;

    @XmlElement(name = "APP_HOLD_DEACTIVE_RMKS")
    private String holdDeactiveRemarks;

    @XmlElement(name = "APP_STATUS_DELTA")
    private String statusDelta;

    @XmlElement(name = "APP_KYC_MODE")
    private String kycMode;

    @XmlElement(name = "APP_IPV_FLAG")
    private String ipvFlag;

    @XmlElement(name = "ERROR")
    private CvlError cvlError;
}
