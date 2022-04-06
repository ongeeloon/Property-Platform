package com.example.team5ad.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/amenity/{place_name}")
    public ModelAndView placeAmenity (@PathVariable("place_name") String placeName){
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("amenity");
    	mav.addObject("place_name", placeName);
        return mav;
    }
}
