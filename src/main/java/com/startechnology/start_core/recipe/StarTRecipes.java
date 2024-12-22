package com.startechnology.start_core.recipe;
import java.util.function.Consumer;

import com.startechnology.start_core.recipe.categories.AkreyriumLine;
import com.startechnology.start_core.recipe.categories.ResetNBT;

import net.minecraft.data.recipes.FinishedRecipe;

public class StarTRecipes {
    public static final void init(Consumer<FinishedRecipe> provider) {
        ResetNBT.init(provider);
        AkreyriumLine.init(provider);
    }
}
