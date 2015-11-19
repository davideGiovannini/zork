/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.ajaxsample.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author demiurgo
 */
@XmlRootElement(name = "gameState")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameState {

    @XmlElementWrapper(name = "actions")
    @XmlElement(name = "action")
    private List<Action> actions;

    public GameState() {
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Action> getActions() {
        return actions;
    }


}
