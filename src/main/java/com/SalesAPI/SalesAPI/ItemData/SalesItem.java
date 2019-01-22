package com.SalesAPI.SalesAPI.ItemData;

import lombok.Data;
import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity(name = "SalesItem")
@Table(name = "sales_Item")
public class SalesItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_gen")
    @SequenceGenerator(name = "item_id_gen", sequenceName = "item_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String title;
    private String description;
    private int price;
    private int stock;
    private String country;
    private String city;
    private String street;
    private String gpsCoordinates;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SalesItemComment> comments = new LinkedList<>();

    public SalesItem(String title, String description, int price, int stock, String country, String city, String street, String gpsCoordinates) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.country = country;
        this.city = city;
        this.street = street;
        this.gpsCoordinates = gpsCoordinates;

    }

    public SalesItem() {
    }
}
