package com.tmao.crm.commons.web;

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

// dsafsadfs

// <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
// <jsp:include page="header.jsp"></jsp:include>
//
// <h3>Error</h3>
// <br>
//
// <table class='table table-hover table-responsive table-bordered'>
// <tr>
// <td><b>Date</b></td>
// <td><c:out value="${timestamp}"></c:out></td>
// </tr>
// <tr>
// <td><b>Error</b></td>
// <td><c:out value="${error}"></c:out></td>
// </tr>
// <tr>
// <td><b>Status</b></td>
// <td><c:out value="${status}"></c:out></td>
// </tr>
// <tr>
// <td><b>Message</b></td>
// <td><c:out value="${message}"></c:out></td>
// </tr>
// <tr>
// <td><b>Trace</b></td>
// <td><c:out value="${trace}"></c:out></td>
// </tr>
// </table>
