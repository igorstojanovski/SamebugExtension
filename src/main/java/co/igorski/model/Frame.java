package co.igorski.model;

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
    private Method method;
    @JsonProperty("location")
    private String location;

}