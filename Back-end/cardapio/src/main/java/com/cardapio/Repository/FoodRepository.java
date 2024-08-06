package com.cardapio.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardapio.model.Food;

public interface FoodRepository extends JpaRepository<Food,Long> {
    
    //uma interface que herda de outra interface genérica do jpa, recebe dois parâmetros, o model e o tipo do Id desse objeto

}
