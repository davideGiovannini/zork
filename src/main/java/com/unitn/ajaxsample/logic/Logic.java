package com.unitn.ajaxsample.logic;

import com.unitn.ajaxsample.model.Action;
import com.unitn.ajaxsample.model.GameState;
import com.unitn.ajaxsample.model.Place;

import java.util.List;
import java.util.Random;

import static com.unitn.ajaxsample.model.Place.PlaceType.values;

/**
 * Created by demiurgo on 11/20/15.
 */
public class Logic {

    static Random random = new Random();



    public static void update(GameState state, final Action action){
        // UPDATE LOCATION
        state.getPlace().performAction(action, random);

        //UPDATE ACTIONS
        List<Action> actions = state.getActions();
        actions.clear();
        actions.addAll(state.getPlace().getPossibleActions());

    }




    public static GameState getStartingState(){
        GameState state = new GameState();

        Place place = new Place();
        place.setPlaceType(values()[random.nextInt(values().length)]);
        state.setPlace(place);

        state.setActions(place.getPossibleActions());

        return state;
    }



}
