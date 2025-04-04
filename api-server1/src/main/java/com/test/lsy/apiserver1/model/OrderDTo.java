package com.test.lsy.apiserver1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTo {
    private int id;
    private String name;
    private String category;
    private String color;
    private double price;
}
