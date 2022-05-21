package com.example.Project.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "laptop_table")
@Data
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brend;
    private String model;
    private String price;
    private String imageLink;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "account_id")
    private Account theAccount;

    @Override
    public String toString() {
return "Laptop{" +
"id=" +id +
        ", brend=' " +brend + '\''+
        ", model='" + model + '\'' +
        ", price='" + price + '\'' +
        ", imageLink='" + imageLink + '\'' +
        '}';

    }
}
