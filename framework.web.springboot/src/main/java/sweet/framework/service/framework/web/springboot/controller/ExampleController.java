package sweet.framework.service.framework.web.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sweet.framework.service.IUserService;

import java.util.Date;

@RestController
@RequestMapping("/")
public class ExampleController {

    private final IUserService userService;

    @Autowired
    public ExampleController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String index() {
        return new Date().toString();
    }

    @RequestMapping("/spring")
    public String spring() {
        return userService.getTime().toString();
    }


    @RequestMapping("/db")
    public String db() {
        return userService.getUserById(1L).toString();
    }


}
