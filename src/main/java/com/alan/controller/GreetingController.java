package com.alan.controller;

import com.alan.core.Wrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by alan on 17/1/9.
 */
@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    @ResponseBody
    public Wrapper greeting() {
        return Wrapper.SUCCESS;
    }
}
