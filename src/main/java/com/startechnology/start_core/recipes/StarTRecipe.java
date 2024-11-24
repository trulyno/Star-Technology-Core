package com.startechnology.start_core.recipes;

import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;

public interface StarTRecipe {
    public void initRecipes(Consumer<FinishedRecipe> provider);
}
