package com.simple.test.work.TestWork.repo;

import com.simple.test.work.TestWork.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}