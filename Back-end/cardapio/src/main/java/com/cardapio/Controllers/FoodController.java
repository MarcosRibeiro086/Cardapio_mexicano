package com.cardapio.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardapio.Repository.FoodRepository;
import com.cardapio.model.Food;
import com.cardapio.model.FoodRequestDTO;
import com.cardapio.model.FoodResponseDTO;


//indicar ao spring que é um controller
@RestController
//mapeando o endpoint
@RequestMapping("food")
public class FoodController {

    //injetando dependencias
    @Autowired
    private FoodRepository repository;

    //usado apenas no exemplo, não é uma boa prática permitir tudo
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Void> saveFood(@RequestBody FoodRequestDTO data){
        Food foodData =new Food(data);
        repository.save(foodData);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> getById(@PathVariable Long id){
        Optional<Food> foodOptional=repository.findById(id);
        if (foodOptional.isPresent()){
            return ResponseEntity.ok(new FoodResponseDTO(foodOptional.get()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping
    public List <FoodResponseDTO>getAll(){

        //pegando os dados da entidade, funilando e criando um dto para apresentar no front end
        List<FoodResponseDTO> foodList=repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // Status 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Status 404 Not Found
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFood(@RequestBody FoodRequestDTO data, @PathVariable Long id) {
        Optional<Food> existingFoodOptional = repository.findById(id);
        if (existingFoodOptional.isPresent()) {
            Food existingFood = existingFoodOptional.get();
            existingFood.updateFromDTO(data);
            repository.save(existingFood);
            return ResponseEntity.ok().build(); // Status 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Status 404 Not Found
        }
    }

}
