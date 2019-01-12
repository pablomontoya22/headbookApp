package app.controllers.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorWebController implements ErrorController {

    @GetMapping("/error")
    public ModelAndView handleError() {
        return new ModelAndView("redirect:/");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
