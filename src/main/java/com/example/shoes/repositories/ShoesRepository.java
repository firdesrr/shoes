package com.example.shoes.repositories;

import com.example.shoes.entities.Shoes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoesRepository extends CrudRepository<Shoes,Long> {
}
