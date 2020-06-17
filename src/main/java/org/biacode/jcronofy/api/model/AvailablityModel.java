package org.biacode.jcronofy.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * User: Syuzanna Eprikyan
 * Date: 1/12/18
 * Time: 4:45 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailablityModel implements Serializable {

    //region Properties
    //endregion

    //region Constructors
    public AvailablityModel() {
        // default constructor
    }
    //endregion

    //region Equals, HashCode and ToString
    //endregion

    //region Properties getters and setters
    //endregion
}
