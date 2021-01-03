package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventAttendeesModel implements Serializable {

    @JsonProperty("invite")
    private List<AttendeesModel> invite;

    @JsonProperty("remove")
    private List<AttendeesModel> remove;

    public EventAttendeesModel(List<AttendeesModel> invite, List<AttendeesModel> remove) {
        this.invite = invite;
        this.remove = remove;
    }

    public void setInvite(List<AttendeesModel> invite) {
        this.invite = invite;
    }

    public void setRemove(List<AttendeesModel> remove) {
        this.remove = remove;
    }
}
