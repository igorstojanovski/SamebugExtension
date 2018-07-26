package co.igorski.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"typeName", "message", "frames", "more"})
@Getter
@Setter
@NoArgsConstructor
public class Cause {

    @JsonProperty("typeName")
    private String typeName;
    @JsonProperty("message")
    private String message;
    @JsonProperty("frames")
    private List<Frame> frames = null;
    @JsonProperty("more")
    private Integer more;
}