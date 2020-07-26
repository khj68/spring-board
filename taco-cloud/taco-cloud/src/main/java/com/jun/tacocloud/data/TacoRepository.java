package com.jun.tacocloud.data;

import com.jun.tacocloud.Taco;

import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long>{
    // Taco save(Taco design);
}