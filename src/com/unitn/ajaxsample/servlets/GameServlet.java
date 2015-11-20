/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.ajaxsample.servlets;

import com.unitn.ajaxsample.model.Action;
import com.unitn.ajaxsample.model.GameState;
import com.unitn.ajaxsample.model.Place;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.unitn.ajaxsample.model.Place.PlaceType.values;

/**
 * @author demiurgo
 */
public class GameServlet extends HttpServlet {

    Random random = new Random();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        GameState state = (GameState) session.getAttribute("gameState");

        if (state == null) {
            state = new GameState();
            List<Action> actions = new ArrayList<Action>(3);
            actions.add(new Action(Action.ActionType.WAIT));
            actions.add(new Action(Action.ActionType.ENTER));
            actions.add(new Action(Action.ActionType.DESCEND));

            actions.add(new Action(Action.ActionType.EXIT));

            state.setActions(actions);

            Place place = new Place();
            place.setPlaceType(values()[random.nextInt(values().length)]);
            state.setPlace(place);

            session.setAttribute("gameState", state);
        }

        writeGameState(state, response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String action = request.getQueryString();
        if(action.equals("GET_CROWN")){
            session.invalidate();
            session = null;
        }


        if (session == null) {
            response.sendRedirect("game.html");
            return;
        }





        GameState state = (GameState) session.getAttribute("gameState");

        List<Action> actions = state.getActions();
        actions.clear();
        actions.add(new Action(Action.ActionType.GET_CROWN));


        Place place = new Place();
        place.setPlaceType(Place.PlaceType.THRONE_ROOM);
        state.setPlace(place);

        writeGameState(state, response.getOutputStream());

    }


    private void writeGameState(GameState state, OutputStream out) {
        try {
            JAXBContext context = JAXBContext.newInstance(GameState.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(state, out);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

}
