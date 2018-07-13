package org.igorski.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "method",
        "location"
})
@Getter
@Setter
@NoArgsConstructor
public class Frame {

    @JsonProperty("method")
    public Method method;
    @JsonProperty("location")
    public String location;

    public Frame withMethod(Method method) {
        this.method = method;
        return this;
    }

    public Frame withLocation(String location) {
        this.location = location;
        return this;
    }

}