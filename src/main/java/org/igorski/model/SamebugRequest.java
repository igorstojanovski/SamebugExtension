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
        "cause"
})
@Getter
@Setter
@NoArgsConstructor
public class SamebugRequest {

    @JsonProperty("typeName")
    public String typeName;
    @JsonProperty("message")
    public String message;
    @JsonProperty("frames")
    public List<Frame> frames = null;
    @JsonProperty("cause")
    public Cause cause;

    public SamebugRequest withTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public SamebugRequest withMessage(String message) {
        this.message = message;
        return this;
    }

    public SamebugRequest withFrames(List<Frame> frames) {
        this.frames = frames;
        return this;
    }

    public SamebugRequest withCause(Cause cause) {
        this.cause = cause;
        return this;
    }

}