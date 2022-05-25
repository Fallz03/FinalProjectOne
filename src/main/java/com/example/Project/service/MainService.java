package com.example.Project.service;

import com.example.Project.model.Account;
import com.example.Project.model.Laptop;
import com.example.Project.repository.AccountRepository;
import com.example.Project.repository.LaptopRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MainService {

    private final AccountRepository accountRepository;
    private final LaptopRepository laptopRepository;




    public Account getAccountByUsername(String username){
        return accountRepository.getAccountByUsername(username);
    }


    public void addLaptop(Laptop theLaptop , MultipartFile multipartFile){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Account owner_account = getAccountByUsername(authentication.getName());
        theLaptop.setTheAccount(owner_account);

        String imageName = multipartFile.getOriginalFilename() + UUID.randomUUID();

        File file = new File("src/main/resources/static/image_base/user_"+owner_account.getId());
        if(!file.exists()){
            file.mkdir();
        }

        Path path = Paths.get
                (file.getPath(),"/" + imageName);

        try {
            multipartFile.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        theLaptop.setImageLink(imageName);
        laptopRepository.save(theLaptop);


    }


    public void saveAccount(Account theAccount) {
        accountRepository.save(theAccount);
    }

    public List<Laptop> getLaptopList() {
        return laptopRepository.findAll();
    }

    public List<Laptop> getMyLaptopList(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        Account owner_account = getAccountByUsername(authentication.getName());
        System.out.println(authentication);

        List<Laptop> laptopList = new ArrayList<>();
        for(Laptop laptop : laptopRepository.findAll()){
            if(laptop.getTheAccount().getId() ==owner_account.getId()){
                laptopList.add(laptop);
            }
        }
        return laptopList;
    }


    public Laptop getLaptopById(long id){
        return laptopRepository.getLaptopById(id);
    }


    public void deleteLaptopById(long id) {
       laptopRepository.deleteById(id);
    }
}
