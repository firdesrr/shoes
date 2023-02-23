package com.example.shoes.controllers;

import com.example.shoes.entities.Shoes;
import com.example.shoes.repositories.ShoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ShoesController {
    @Autowired
    ShoesRepository shoesRepository;
  @GetMapping ("/create")
    public String createShoes(Shoes shoes, Model model){
      model.addAttribute("shoes", new Shoes());
           return "/addShoes";
  }
  @PostMapping ("/submit")
    public ModelAndView submitShoes(@Valid Shoes shoes, BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
          return new ModelAndView("/create");
      } else {
          shoesRepository.save(shoes);
          return new ModelAndView("redirect:/allShoes");
      }
  }
      @GetMapping ("/allShoes")
              public  String getAllShoes (Model model) {
          Iterable <Shoes> shoesList=shoesRepository.findAll();
          model.addAttribute("shoesList",shoesList);
      return  "/allShoes";
      }
@GetMapping ("/editShoes/{shoesId}")
    public String editShoes(@PathVariable (name="shoesId") Long shoesId, Model model ) {
    Optional<Shoes> optionalShoes = shoesRepository.findById(shoesId);
    if (optionalShoes.isPresent()) {
        model.addAttribute("shoes", optionalShoes.get());
    } else {
        model.addAttribute("shoes", "Error!");
        model.addAttribute("errorMsg", "Not existing shoes with id: " + shoesId);
    }
    return "/editShoes";
}
@PostMapping("/update")
public ModelAndView updateShoes(@Valid Shoes shoes, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
        return new ModelAndView("/editShoes");
    } else {
        shoesRepository.save(shoes);
        return new ModelAndView("redirect:/allShoes");
    }
}
@PostMapping ("/delete/{shoesId}")
public ModelAndView deleteShoes(@PathVariable (name="shoesId") Long shoesId, Model model ) {
      shoesRepository.deleteById(shoesId);
    return new ModelAndView("redirect:/allShoes");
}









}
