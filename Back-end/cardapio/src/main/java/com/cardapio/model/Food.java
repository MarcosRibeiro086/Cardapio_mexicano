package com.cardapio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="foods")
@Entity(name="foods")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Food {

     
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private Integer price;

    //constructor para requests do usuário
    public Food(FoodRequestDTO data){
        this.title=data.title();
        this.image=data.image();
        this.price=data.price();
        
    }

    public void updateFromDTO(FoodRequestDTO data) {
        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.image() != null) {
            this.image = data.image();
        }
        if (data.price() != null) {
            this.price = data.price();
        }
    }
}
