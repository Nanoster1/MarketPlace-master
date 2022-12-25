package com.example.marketplace.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarketPlaceController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/add")
    public String add() {
        return "add";
    }
}
