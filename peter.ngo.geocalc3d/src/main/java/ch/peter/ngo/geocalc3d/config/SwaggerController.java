package ch.peter.ngo.geocalc3d.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {
    @RequestMapping("/")
    public String home() {
        return "redirect:/swagger-ui.html";
    }
}