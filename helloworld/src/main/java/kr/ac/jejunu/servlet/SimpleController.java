package kr.ac.jejunu.servlet;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


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
    public String error(NullPointerException e) {
        return "error";
    }


    @RequestMapping(path="/upload", method= RequestMethod.GET)
    public String upload() {
        return "upload";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        File path = new File(request.getServletContext().getRealPath("/")
                +"/WEB-INF/static/"+file.getOriginalFilename());
        FileOutputStream fileOutputStream= new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        ModelAndView modelAndView = new ModelAndView("upload");
        modelAndView.addObject("url", "/images/"+ file.getOriginalFilename());
        return modelAndView;
    }
}


