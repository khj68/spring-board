package com.jun.tacocloud.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import lombok.extern.slf4j.Slf4j;
import com.jun.tacocloud.Taco;
import com.jun.tacocloud.Ingredient;
import com.jun.tacocloud.Ingredient.Type;
import com.jun.tacocloud.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    
    @GetMapping
    public String showDesignForm(final Model model) {
        // final List<Ingredient> ingredients = Arrays.asList(
        //     new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
        //     new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
        //     new Ingredient("GRBF", "Groud Beef", Type.PROTEIN),
        //     new Ingredient("CARN", "Carnitas", Type.PROTEIN),
        //     new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
        //     new Ingredient("LETC", "Lettuce", Type.VEGGIES),
        //     new Ingredient("CHED", "Cheddar", Type.CHEESE),
        //     new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
        //     new Ingredient("SLSA", "Salsa", Type.SAUCE),
        //     new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        // );

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        final Type[] types = Ingredient.Type.values();
        for (final Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());
        
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        
        log.info("Processing design: " + design);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
        final List<Ingredient> ingredients, final Type type) {
            return ingredients
                    .stream()
                    .filter(x -> x.getType().equals(type))
                    .collect(Collectors.toList());
        
        }
}