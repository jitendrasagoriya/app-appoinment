package com.js.appointment.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.js.autenticationclient.auth.CommonAuthentication;
import org.js.autenticationclient.auth.impl.CommonAuthenticationImpl;
import org.js.autenticationclient.bean.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.sql.Timestamp;

@Component
public class AuthenticatorInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticatorInterceptor.class);


    private CommonAuthentication authentication = new CommonAuthenticationImpl();




    /**
     * This is not a good practice to use sysout. Always integrate any logger with
     * your application. We will discuss about integrating logger with spring boot
     * application in some later article
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if (logger.isDebugEnabled())
            logger.debug("In preHandle we are Intercepting the Request");
        if (logger.isDebugEnabled())
            logger.debug("---------------------------------------------");
        String requestURI = request.getRequestURI();
        Integer personId = ServletRequestUtils.getIntParameter(request, "personId", 0);

        /*****************************
         * URL NOT CHECK AUTHENTICATION
         *************************************/

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().contains("/api/doctor/auth")) {
            return true;
        }

        if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().contains("api/doctor/")) {
            return true;
        }

        if (request.getMethod().equalsIgnoreCase("GET") && request.getRequestURI().contains("/api/token/")) {
            return true;
        }

        if (request.getRequestURI().contains("/api/doctor/view/")) {
            return true;
        }

        if ( request.getRequestURI().contains("/error")) {
            return true;
        }





        /*************************************
         * CHECK HEADER
         *************************************/

        String accessToken = request.getHeader("X-AUTH-LOG-HEADER");
        if (accessToken == null || accessToken.isEmpty()) {
            response.getWriter().write("Authentication failed.");
            response.setStatus(401);
            return false;
        }

        /**************************************
         * CHECK AUTHENTICTION
         *************************************/
         HttpSession session = request.getSession();
         Boolean isValid = false;//service.isValidUser(accessToken);

         Authentication auth = null;
         if(session.getAttribute("ID") == null) {
             auth = authentication.authenticate(accessToken);

             if(auth != null) {
                 isValid = true;
                 session.setAttribute("ID", auth.getUserId());
                 session.setAttribute("X-AUTH-LOG-SESSION", accessToken);
             }

             /*************************************
              *        CHECK SESSION TIME OUT     *
              *************************************/
             if(auth != null) {
                 Timestamp lastLogin = auth.getLastLogin();
                 Long lastLoginInMills = lastLogin.getTime();
                 long milliseconds = auth.getExpaireDay() * 24 * 60 * 60 * 1000;
                 if (System.currentTimeMillis() > (lastLoginInMills + milliseconds)) {
                     response.getWriter().write("Session expire. Please login again");
                     response.setStatus(401);
                     return false;
                 }
             }
         } else {
                 isValid = true;
         }



        if (logger.isDebugEnabled())
            logger.debug("isValid :" + (isValid == null));
        if (!isValid) {
            response.getWriter().write("Authentication failed.");
            response.setStatus(401);
            return false;
        }
        // if(logger.isDebugEnabled()) logger.debug(accessToken);
        if (logger.isDebugEnabled())
            logger.debug("RequestURI::" + requestURI + " || Search for Person with personId ::" + personId);
        if (logger.isDebugEnabled())
            logger.debug("---------------------------------------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
            throws Exception {
        if (logger.isDebugEnabled())
            logger.debug("---------------------------------------------");
        if (logger.isDebugEnabled())
            logger.debug("In postHandle request processing " + "completed by @RestController");
        if (logger.isDebugEnabled())
            logger.debug("---------------------------------------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
            throws Exception {
        if (logger.isDebugEnabled())
            logger.debug("---------------------------------------------");
        if (logger.isDebugEnabled())
            logger.debug("In afterCompletion Request Completed");
        if (logger.isDebugEnabled())
            logger.debug("---------------------------------------------");
    }
}
