package io.github.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class TestDto implements Serializable {

    @JsonProperty("name")
    private String clientId;
    @JsonProperty("email")
    private String emailId;

    public TestDto(final String clientId, final String emailId) {
        this.clientId = clientId;
        this.emailId = emailId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }
}

