package com.example.Project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laptop_account_table")
@Data
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
    private String username;
    private String password;
    private String name_surname;
    private String gmail;
    private String phone_number;

    @OneToMany
    private List<Laptop> laptopList;
}
