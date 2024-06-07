package com.scm.helpers;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpSession;
@Component
public class SessionHelper {

    public static void removeMessage(){

       try {
        System.out.println("remove message=====================");
        HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("message");
       } catch (Exception e) {
        e.printStackTrace();
       }
    }

}
