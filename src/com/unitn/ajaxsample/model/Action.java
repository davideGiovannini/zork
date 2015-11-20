/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.ajaxsample.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author demiurgo
 */
public class Action {

    @XmlAttribute(name = "class")
    private final ActionType type;

    public Action(ActionType type) {
        this.type = type;
    }

    public static enum ActionType {
        WAIT, ENTER, EXIT, DESCEND, GET_CROWN, GO_NORTH, GO_WEST, GO_EAST, GO_SOUTH;
    }
}
