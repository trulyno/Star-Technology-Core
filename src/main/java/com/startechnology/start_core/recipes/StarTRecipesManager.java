package com.startechnology.start_core.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.startechnology.start_core.recipes.categories.AkreyriumLine;
import com.startechnology.start_core.recipes.categories.ResetNBT;

import net.minecraft.data.recipes.FinishedRecipe;

public class StarTRecipesManager {
    private static List<StarTRecipe> RECIPES = new ArrayList<>();

    static {
        RECIPES.add(new AkreyriumLine());
        RECIPES.add(new ResetNBT());
    }

    // Initialise all recipes put in to RECIPES_INITIALISED.
    public static void initRecipes(Consumer<FinishedRecipe> provider) {
        RECIPES.forEach((recipe) -> {
            recipe.initRecipes(provider);
        });
    }
}
