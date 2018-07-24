package org.igorski.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "packageName",
        "className",
        "methodName"
})
@Getter
@Setter
@NoArgsConstructor
public class Method {

    @JsonProperty("packageName")
    private String packageName;
    @JsonProperty("className")
    private String className;
    @JsonProperty("methodName")
    private String methodName;
}