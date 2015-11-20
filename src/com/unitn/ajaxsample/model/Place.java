package com.unitn.ajaxsample.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by demiurgo on 11/19/15.
 */
@XmlRootElement(name = "place")
@XmlAccessorType(XmlAccessType.FIELD)
public class Place {

    @XmlAttribute(name = "class")
    private PlaceType placeType;




    public List<Action> getPossibleActions(){
        List<Action> actions = new LinkedList<>();
        switch (placeType){
            case GRAVEYARD:
                actions.add(new Action(Action.ActionType.ENTER));
                actions.add(new Action(Action.ActionType.GO_NORTH));
                actions.add(new Action(Action.ActionType.GO_EAST));
                break;
            case WOODS:
                actions.add(new Action(Action.ActionType.GO_SOUTH));
                actions.add(new Action(Action.ActionType.GO_EAST));
                break;
            case MINES:
                actions.add(new Action(Action.ActionType.GO_WEST));
                actions.add(new Action(Action.ActionType.GO_NORTH));
                break;
            case THRONE_ROOM:
                actions.add(new Action(Action.ActionType.GET_CROWN));
                actions.add(new Action(Action.ActionType.EXIT));
                break;
            case SWAMP:
                actions.add(new Action(Action.ActionType.GO_WEST));
                actions.add(new Action(Action.ActionType.GO_SOUTH));
                break;
            case CASTLE:
                actions.add(new Action(Action.ActionType.ENTER));
                actions.add(new Action(Action.ActionType.GO_NORTH));
                break;
            case CHURCH:
                actions.add(new Action(Action.ActionType.GO_NORTH));
                actions.add(new Action(Action.ActionType.GO_WEST));
                actions.add(new Action(Action.ActionType.GO_EAST));
                actions.add(new Action(Action.ActionType.GO_SOUTH));
                break;
            case TOMB:
                actions.add(new Action(Action.ActionType.EXIT));
                break;
        }
        actions.add(new Action(Action.ActionType.WAIT));
        return actions;
    }


    public void performAction(Action action){
        switch (action.getType()){
            case WAIT:
                break;
            case ENTER:
                switch (placeType) {
                    case GRAVEYARD:
                        placeType = PlaceType.TOMB;
                        break;
                    case CASTLE:
                        placeType = PlaceType.THRONE_ROOM;
                        break;
                }
                break;
            case EXIT:
                switch (placeType) {
                    case THRONE_ROOM:
                        placeType = PlaceType.CASTLE;
                        break;
                    case TOMB:
                        placeType = PlaceType.GRAVEYARD;
                        break;
                }
                break;
            case DESCEND:
                break;
            case GET_CROWN:
                break;
            case GO_NORTH:
                switch (placeType) {
                    case GRAVEYARD:
                        placeType = PlaceType.WOODS;
                        break;
                    case MINES:
                        placeType = PlaceType.SWAMP;
                        break;
                    case CASTLE:
                        placeType = PlaceType.CHURCH;
                        break;
                    case CHURCH:
                        placeType = PlaceType.WOODS;
                        break;
                }
                break;
            case GO_WEST:
                switch (placeType) {
                    case MINES:
                        placeType = PlaceType.CHURCH;
                        break;
                    case SWAMP:
                        placeType = PlaceType.WOODS;
                        break;
                    case CHURCH:
                        placeType = PlaceType.GRAVEYARD;
                        break;
                }
                break;
            case GO_EAST:
                switch (placeType) {
                    case CHURCH:
                        placeType = PlaceType.MINES;
                        break;
                    case WOODS:
                        placeType = PlaceType.SWAMP;
                        break;
                    case GRAVEYARD:
                        placeType = PlaceType.CHURCH;
                        break;
                }
                break;
            case GO_SOUTH:
                switch (placeType) {
                    case WOODS:
                        placeType = PlaceType.CHURCH;
                        break;
                    case SWAMP:
                        placeType = PlaceType.MINES;
                        break;
                    case CHURCH:
                        placeType = PlaceType.CASTLE;
                        break;
                }
                break;
        }
    }





    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    public static enum PlaceType {
        GRAVEYARD, WOODS, MINES, THRONE_ROOM, SWAMP, CASTLE, CHURCH, TOMB
    }
}
