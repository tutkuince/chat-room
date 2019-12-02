package com.muditasoft.chatroom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@RestController
public class IndexController {

    /**
     * Chatroom Page
     */
    @GetMapping("/chat")
    public ModelAndView index(String username, HttpServletRequest request) throws UnknownHostException {
        //TODO: add code for login to chatroom.

        StringBuffer url = request.getRequestURL();
        String wsURL = url.toString().replaceFirst("http:", "ws:") + "/";

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chat");
        modelAndView.addObject("username", username);
        modelAndView.addObject("wsURL", wsURL);

        return modelAndView;
    }
}
