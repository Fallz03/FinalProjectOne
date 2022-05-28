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

import static java.lang.Thread.sleep;

@Controller
@RequestMapping("/laptop-shop")
@AllArgsConstructor
public class MainController {
    private final MainService mainService;
    private List<Laptop> purchaseList = new ArrayList<>();


    @GetMapping
    public String getPage(){

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


        return "redirect:/laptop-shop/shop";
    }
@GetMapping("/info/{id}")
    public String getInfoPage(
            @PathVariable("id") long id,
    Model model
            ){
        Laptop theLaptop = mainService.getLaptopById(id);
        model.addAttribute("laptop_info" ,theLaptop);
        return "infopage";
    }

    @GetMapping("/basket/buy/delete/{id}")
    public String deleteProcess(
            @PathVariable("id") long id
    ) {

purchaseList.removeIf(laptop -> laptop.getId() == id);




        return "redirect:/laptop-shop/shop";
    }
    @GetMapping("/basket/buy/purchase")
    public String purchaseProcess() throws InterruptedException {
        purchaseList.clear();
        sleep(5000);
        return "redirect:/laptop-shop/shop";
    }

    }

