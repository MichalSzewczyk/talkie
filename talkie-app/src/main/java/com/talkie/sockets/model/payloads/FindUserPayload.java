package com.talkie.sockets.model.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"listOfUsers"})
public class FindUserPayload {
    @JsonProperty("letters")
    private String letters;

    @JsonProperty("letters")
    public String getLetters() {
        return letters;
    }

    @JsonProperty("letters")
    public void setLetters(String letters) {
        this.letters = letters;
    }
}
