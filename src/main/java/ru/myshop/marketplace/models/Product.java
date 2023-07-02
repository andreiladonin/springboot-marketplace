package ru.myshop.marketplace.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CartImage> cartImages = new ArrayList<>();

    private String previewNameImage;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private  void init(){
        dateOfCreated = LocalDateTime.now();

    }
    @Column(columnDefinition = "integer default 0")
    private Integer oftenBuy;

    public Integer getOftenBuy() {
        return oftenBuy;
    }

    public void setOftenBuy(Integer oftenBuy) {
        this.oftenBuy = oftenBuy;
    }

    public void addImage(CartImage image){
        image.setProduct(this);
        cartImages.add(image);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CartImage> getCartImages() {
        return cartImages;
    }

    public void setCartImages(List<CartImage> cartImages) {
        this.cartImages = cartImages;
    }

    public String getPreviewNameImage() {
        return previewNameImage;
    }

    public void setPreviewNameImage(String previewNameImage) {
        this.previewNameImage = previewNameImage;
    }

    public LocalDateTime getDateOfCreated() {
        return dateOfCreated;
    }

    public void setDateOfCreated(LocalDateTime dateOfCreated) {
        this.dateOfCreated = dateOfCreated;
    }
}
