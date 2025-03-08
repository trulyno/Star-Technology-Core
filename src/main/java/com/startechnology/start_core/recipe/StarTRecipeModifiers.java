package com.startechnology.start_core.recipe;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.startechnology.start_core.machine.parallel.IStarTPerfectParallelControllerMixin;
import com.startechnology.start_core.machine.parallel.IStarTPerfectParallelHatch;

public class StarTRecipeModifiers {
    public static final RecipeModifier PERFECTED_PARALLEL = StarTRecipeModifiers::hatchPerfectParallel;

    public static ModifierFunction hatchPerfectParallel(MetaMachine machine, GTRecipe recipe) {
        if (machine instanceof IMultiController controller && controller.isFormed()) {
            IStarTPerfectParallelControllerMixin perfectParallelController = ((IStarTPerfectParallelControllerMixin) (Object) controller);


        }
        return ModifierFunction.IDENTITY;
    }
}
