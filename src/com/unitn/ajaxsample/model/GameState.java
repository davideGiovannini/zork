/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.ajaxsample.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author demiurgo
 */
@XmlRootElement(name = "gameState")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameState {

    @XmlElementWrapper(name = "actions")
    @XmlElement(name = "action")
    private List<Action> actions;

    @XmlElement(name="place")
    private Place place;

    public GameState() {
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Action> getActions() {
        return actions;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
