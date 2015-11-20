/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.ajaxsample.servlets;

import com.unitn.ajaxsample.logic.Logic;
import com.unitn.ajaxsample.model.Action;
import com.unitn.ajaxsample.model.GameState;

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

/**
 * @author demiurgo
 */
public class GameServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        GameState state = (GameState) session.getAttribute("gameState");

        if (state == null) {
            state = Logic.getStartingState();
            session.setAttribute("gameState", state);
        }

        writeGameState(state, response.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session == null) {
            response.sendRedirect("game.html");
            return;
        }


        // GET QUERY ACTION
        final String action = request.getQueryString();
        Action.ActionType actionType;
        try {
            actionType = Action.ActionType.valueOf(action);
        } catch (IllegalArgumentException e) {
            actionType = Action.ActionType.WAIT;
        }

        //TODO improve victory restart
        if(Action.ActionType.GET_CROWN.equals(actionType)){
            response.sendRedirect("game.html");
            session.invalidate();
            return;
        }


        GameState state = (GameState) session.getAttribute("gameState");
        Logic.update(state, actionType);

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
