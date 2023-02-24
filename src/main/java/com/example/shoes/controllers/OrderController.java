package com.example.shoes.controllers;

import com.example.shoes.entities.Order;
import com.example.shoes.entities.Shoes;
import com.example.shoes.repositories.OrderRepository;
import com.example.shoes.repositories.ShoesRepository;
import com.example.shoes.services.OrderServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@Controller
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShoesRepository shoesRepository;
    @Autowired
    OrderServise orderServise;

    @GetMapping("/createOrder")
    public String createOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("shoes", shoesRepository.findAll());
        return "/createOrder";
    }

    @PostMapping("/orderSubmit")
    public ModelAndView orderSubmit(@Valid Order order, BindingResult bindingResult, @RequestParam(name = "shoe1") String shoe1Id,
                                    @RequestParam(name = "shoe2", defaultValue = "null") String shoe2Id,
                                    @RequestParam(name = "shoe3", defaultValue = "null") String shoe3Id) {
       return  orderServise.orderSubmit(order, bindingResult, shoe1Id, shoe2Id, shoe3Id);
    }

}
//final


