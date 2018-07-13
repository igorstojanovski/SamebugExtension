package org.igorski.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "typeName",
        "message",
        "frames",
        "more"
})
@Getter
@Setter
@NoArgsConstructor
public class Cause {

    @JsonProperty("typeName")
    public String typeName;
    @JsonProperty("message")
    public String message;
    @JsonProperty("frames")
    public List<Frame> frames = null;
    @JsonProperty("more")
    public Integer more;

    public Cause withTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public Cause withMessage(String message) {
        this.message = message;
        return this;
    }

    public Cause withFrames(List<Frame> frames) {
        this.frames = frames;
        return this;
    }

    public Cause withMore(Integer more) {
        this.more = more;
        return this;
    }

}