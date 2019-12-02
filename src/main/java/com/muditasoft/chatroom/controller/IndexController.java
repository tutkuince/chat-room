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
    @GetMapping("/index")
    public ModelAndView index(String username, HttpServletRequest request) throws UnknownHostException {
        //TODO: add code for login to chatroom.
        return new ModelAndView("chat");
    }
}
