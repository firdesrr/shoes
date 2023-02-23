package com.example.shoes.services;

import com.example.shoes.entities.Order;
import com.example.shoes.entities.Shoes;
import com.example.shoes.repositories.OrderRepository;
import com.example.shoes.repositories.ShoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServise {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShoesRepository shoesRepository;

    @PostMapping("/orderSubmit")
    public ModelAndView orderSubmit(@Valid Order order, BindingResult bindingResult, @RequestParam(name = "shoe1") String shoe1Id,
                                    @RequestParam(name = "shoe2", defaultValue = "null") String shoe2Id,
                                    @RequestParam(name = "shoe3", defaultValue = "null") String shoe3Id) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/createOrder");
        } else {
            List<Shoes> shoes = new ArrayList<>();
            Shoes shoe1 = shoesRepository.findById(Long.parseLong(shoe1Id)).orElse(null);
            if (shoe1 != null) {
                shoes.add(shoe1);
            }
            if (shoe2Id != "null") {
                Shoes shoe2 = shoesRepository.findById(Long.parseLong(shoe2Id)).orElse(null);
                if (shoe2 != null) {
                    shoes.add(shoe2);
                }
            }
            if (shoe3Id != "null") {
                Shoes shoe3 = shoesRepository.findById(Long.parseLong(shoe3Id)).orElse(null);
                if (shoe3 != null) {
                    shoes.add(shoe3);
                }
            }

            order.setShoes(shoes);
            orderRepository.save(order);
            return new ModelAndView("redirect:/allShoes");
        }
        }
    }


