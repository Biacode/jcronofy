package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventReminderModel implements Serializable {

    @JsonProperty("minutes")
    private Integer minutes;

    public EventReminderModel(Integer minutes) {
        this.minutes = minutes;
    }
}
