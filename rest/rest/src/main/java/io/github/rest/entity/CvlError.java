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
public class CvlError implements Serializable {

    @XmlElement(name = "ERROR_CODE")
    private String errorCode;

    @XmlElement(name = "ERROR_MSG")
    private String errorMessage;
}
