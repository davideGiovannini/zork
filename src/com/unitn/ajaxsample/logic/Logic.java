package com.unitn.ajaxsample.logic;

import com.unitn.ajaxsample.model.Action;
import com.unitn.ajaxsample.model.GameState;
import com.unitn.ajaxsample.model.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.unitn.ajaxsample.model.Place.PlaceType.values;

/**
 * Created by demiurgo on 11/20/15.
 */
public class Logic {

    static Random random = new Random();



    public static void update(GameState state, final Action.ActionType action){

        List<Action> actions = state.getActions();
        actions.clear();
        actions.add(new Action(Action.ActionType.GET_CROWN));


        Place place = new Place();
        place.setPlaceType(Place.PlaceType.THRONE_ROOM);
        state.setPlace(place);

    }




    public static GameState getStartingState(){
        GameState state = new GameState();
        List<Action> actions = new ArrayList<Action>(3);
        actions.add(new Action(Action.ActionType.WAIT));
        actions.add(new Action(Action.ActionType.ENTER));
        actions.add(new Action(Action.ActionType.DESCEND));

        actions.add(new Action(Action.ActionType.EXIT));

        state.setActions(actions);

        Place place = new Place();
        place.setPlaceType(values()[random.nextInt(values().length)]);
        state.setPlace(place);

        return state;
    }



}
