package io.github.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
public class CompletePanStatusXmlResponse implements Serializable {

    @XmlElement(name = "APP_PAN_INQ")
    private CvlPanEnquiry panEnquiry;

    @XmlElement(name = "APP_PAN_SUMM")
    private CvlPanSummary panSummary;
}
