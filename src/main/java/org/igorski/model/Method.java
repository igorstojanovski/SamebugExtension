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
    public String packageName;
    @JsonProperty("className")
    public String className;
    @JsonProperty("methodName")
    public String methodName;

    public Method withPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Method withClassName(String className) {
        this.className = className;
        return this;
    }

    public Method withMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

}