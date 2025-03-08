package com.startechnology.start_core.recipe;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;
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
    public static final RecipeModifier PERFECT_PARALLEL = StarTRecipeModifiers::hatchPerfectParallel;

    public static ModifierFunction hatchPerfectParallel(MetaMachine machine, GTRecipe recipe) {
        if (machine instanceof IMultiController controller && controller.isFormed()) {
            IStarTPerfectParallelControllerMixin perfectParallelController = ((IStarTPerfectParallelControllerMixin) (Object) controller);
            
            IParallelHatch perfectParallelHatch = perfectParallelController.getPerfectParallelHatchStarT();

            if (perfectParallelHatch == null) return ModifierFunction.IDENTITY;
            
            int parallels = perfectParallelHatch.getCurrentParallel();
            
            if (parallels == 1) return ModifierFunction.IDENTITY;

            return ModifierFunction.builder()
                .modifyAllContents(ContentModifier.multiplier(parallels))
                .parallels(parallels)
                .build();
        }
        return ModifierFunction.IDENTITY;
    }
}
