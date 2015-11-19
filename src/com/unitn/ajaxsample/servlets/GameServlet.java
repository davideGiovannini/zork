/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unitn.ajaxsample.servlets;

import com.unitn.ajaxsample.model.Action;
import com.unitn.ajaxsample.model.GameState;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author demiurgo
 */
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GameState state = new GameState();
        List<Action> actions = new ArrayList<Action>(3);
        actions.add(new Action(Action.ActionType.WAIT));
        actions.add(new Action(Action.ActionType.ENTER));
        actions.add(new Action(Action.ActionType.DESCEND));

        actions.add(new Action(Action.ActionType.EXIT));

        state.setActions(actions);

        try {
            JAXBContext context = JAXBContext.newInstance(GameState.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(state, response.getOutputStream());

        } catch (JAXBException ex) {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getOutputStream().println(request.getQueryString());

    }

}
