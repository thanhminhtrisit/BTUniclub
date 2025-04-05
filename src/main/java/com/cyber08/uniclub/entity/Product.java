package com.cyber08.uniclub.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String information;
    private double price;
    private String description;

    @CreationTimestamp
    private Date create_date;

    @OneToMany(mappedBy = "product")
    private List<Varient> varients;

}
