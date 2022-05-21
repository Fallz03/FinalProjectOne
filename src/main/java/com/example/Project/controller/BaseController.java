package com.example.Project.controller;

import com.example.Project.model.Laptop;
import com.example.Project.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/laptop-base")
@AllArgsConstructor
public class BaseController {
    @Autowired
    private final MainService mainService;


    @GetMapping
    public String getPage(Model model){
        model.addAttribute("laptop_list",mainService.getMyLaptopList());
        return "base";
    }

    @GetMapping("/add-laptop")
    public String getAddLaptopPage(Model model){
        model.addAttribute("laptop", new Laptop());
        return "add-laptop";
    }
    @PostMapping("/save")
    public String saveLaptop(
            @ModelAttribute("laptop") Laptop theLaptop,

            @RequestParam("laptop_image") MultipartFile multipartFile
    ){

        System.out.println(multipartFile);
        mainService.addLaptop(theLaptop,multipartFile);
        return "redirect:/laptop-base";
    }}

