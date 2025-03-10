package com.startechnology.start_core.recipe;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.startechnology.start_core.machine.parallel.IStarTAbsoluteParallelControllerMixin;
import com.startechnology.start_core.machine.parallel.IStarTAbsoluteParallelHatch;

public class StarTRecipeModifiers {
    public static final RecipeModifier ABSOLUTE_PARALLEL = StarTRecipeModifiers::hatchAbsoluteParallel;

    public static ModifierFunction hatchAbsoluteParallel(MetaMachine machine, GTRecipe recipe) {
        if (machine instanceof IMultiController controller && controller.isFormed()) {
            IStarTAbsoluteParallelControllerMixin absoluteParallelController = ((IStarTAbsoluteParallelControllerMixin) (Object) controller);
            
            IParallelHatch absoluteParallelHatch = absoluteParallelController.getAbsoluteParallelHatchStarT();

            if (absoluteParallelHatch == null) return ModifierFunction.IDENTITY;
            
            int parallels = absoluteParallelHatch.getCurrentParallel();
            
            if (parallels == 1) return ModifierFunction.IDENTITY;

            return ModifierFunction.builder()
                .modifyAllContents(ContentModifier.multiplier(parallels))
                .parallels(parallels)
                .build();
        }
        return ModifierFunction.IDENTITY;
    }
}
