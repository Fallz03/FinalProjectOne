package com.example.Project.controller;

import com.example.Project.model.Account;
import com.example.Project.model.Laptop;
import com.example.Project.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/laptop-shop")
@AllArgsConstructor
public class MainController {
    private final MainService mainService;
    private List<Laptop> purchaseList = new ArrayList<>();


    @GetMapping
    public String getPage(Model model){
        model.addAttribute("laptop_list", mainService.getLaptopList());
        model.addAttribute("purchaseList" , purchaseList);
        return "main";
    }

    @GetMapping("/shop")
    public String getShop(Model model){
        model.addAttribute("laptop_list", mainService.getLaptopList());
        model.addAttribute("purchaseList" , purchaseList);
        return "shop";
    }

    @PostMapping
    public String saveAccount(
            @ModelAttribute("account") Account theAccount

    ){

        mainService.saveAccount(theAccount);
        return "redirect:/login";
    }

    @GetMapping("/sign-up")
    public String getSignUpPage(Model model){
        model.addAttribute("account", new Account());
        return "signup";
    }


    @GetMapping("/basket/buy/{id}")
    public String buyProcess(
            @PathVariable("id") long id
    ){
        Laptop laptop = mainService.getLaptopById(id);
        if(!purchaseList.contains(laptop)){
            purchaseList.add(laptop);
        }

        System.out.println(purchaseList);
        return "redirect:/laptop-shop";
    }
}
