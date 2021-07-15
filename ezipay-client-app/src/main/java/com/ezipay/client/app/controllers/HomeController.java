package com.ezipay.client.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezipay.client.app.bean.Customer;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
        return "login";
    }
}