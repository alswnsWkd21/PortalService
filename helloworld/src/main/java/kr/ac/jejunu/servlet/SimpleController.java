package kr.ac.jejunu.servlet;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@org.springframework.stereotype.Controller
@RequestMapping("/helloworld")
public class SimpleController {

    @RequestMapping("/hi")
    public ModelAndView hello() throws Exception {
        ModelAndView modelAndView = null;
        modelAndView.addObject("hello", "Hello World!!!");
        return modelAndView;
    }

    @ExceptionHandler(NullPointerException.class)
    public String error(NullPointerException e){
        return "error";
    }
}


