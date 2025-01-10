package com.startechnology.start_core.api.machine;

import com.gregtechceu.gtceu.api.capability.IParallelHatch;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;

public class StarTRecipeModifiers {
    public static RecipeModifier limitedParallelHatch(int limit) {
        return (machine, recipe) -> {
            if (machine instanceof IMultiController controller && controller.isFormed()) {
                int parallels = controller.getParts().stream()
                        .filter(IParallelHatch.class::isInstance)
                        .map(IParallelHatch.class::cast)
                        .findAny()
                        .map(hatch -> ParallelLogic.getParallelAmount(machine, recipe, hatch.getCurrentParallel()))
                        .orElse(1);

                if (parallels == 1) return ModifierFunction.IDENTITY;

                parallels = Math.min(parallels, limit);

                return ModifierFunction.builder()
                        .modifyAllContents(ContentModifier.multiplier(parallels))
                        .eutMultiplier(parallels)
                        .parallels(parallels)
                        .build();
            }
            return ModifierFunction.IDENTITY;
        };
        }
}
