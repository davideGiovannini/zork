package com.unitn.ajaxsample.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by demiurgo on 11/19/15.
 */
@XmlRootElement(name = "place")
@XmlAccessorType(XmlAccessType.FIELD)
public class Place {

    @XmlAttribute(name = "class")
    private PlaceType placeType;


    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public static enum PlaceType {
        GRAVEYARD, WOODS, MINES,
    }
}
