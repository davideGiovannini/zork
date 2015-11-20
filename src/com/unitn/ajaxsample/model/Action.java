/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.ajaxsample.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author demiurgo
 */
public class Action {

    @XmlAttribute(name = "class")
    private final ActionType type;

    @XmlValue
    private String getActionDescription(){
        switch (type) {
            case WAIT:
                return "Wait here";
            case ENTER:
                return "Enter";
            case EXIT:
                return "Exit";
            case DESCEND:
                return "Descend";
            case GET_CROWN:
                return "Get the crown";
            case GO_NORTH:
                return "Go north";
            case GO_WEST:
                return "Go west";
            case GO_EAST:
                return "Go east";
            case GO_SOUTH:
                return "Go south";
            case RESTART_GAME:
                return "New game";
            default:
                return type.name();
        }
    }

    public Action(ActionType type) {
        this.type = type;
    }


    public ActionType getType() {
        return type;
    }

    public static enum ActionType {
        WAIT, ENTER, EXIT, DESCEND, GET_CROWN, GO_NORTH, GO_WEST, GO_EAST, GO_SOUTH, RESTART_GAME
    }
}
