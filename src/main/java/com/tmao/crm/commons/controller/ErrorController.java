package com.tmao.crm.commons.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(final HttpServletRequest request, final Exception exception) {
        final ModelAndView mv = new ModelAndView();

        mv.addObject("exception", exception.getLocalizedMessage());
        mv.addObject("url", request.getRequestURL());

        mv.setViewName("error");
        return mv;
    }

}
