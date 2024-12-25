package com.startechnology.start_core.recipe.logic;

import static com.startechnology.start_core.item.StarTBacteriaItems.BACTERIA_ITEMS;

import org.jetbrains.annotations.Nullable;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IRecipeCapabilityHolder;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType.ICustomRecipeLogic;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.startechnology.start_core.StarTCore;
import com.startechnology.start_core.api.bacteria.StarTBacteriaManager;
import com.startechnology.start_core.api.bacteria.StarTBacteriaStats;
import com.startechnology.start_core.api.custom_tooltips.StarTCustomTooltipsManager;
import com.startechnology.start_core.item.components.StarTBacteriaBehaviour;
import com.startechnology.start_core.recipe.StarTRecipeTypes;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidType;

public class BacterialHydrocarbonHarvesterLogic implements ICustomRecipeLogic {

    @Override
    public @Nullable GTRecipe createCustomRecipe(IRecipeCapabilityHolder holder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCustomRecipe'");
    }
 
    
    @Override
    public void buildRepresentativeRecipes() {
        BACTERIA_ITEMS.stream().forEach(
            bacteria -> {
                ItemStack bacteriaInput = new ItemStack(bacteria.asItem());
                StarTCustomTooltipsManager.writeCustomTooltipsToItem(bacteriaInput.getOrCreateTag(), 
                    "behaviour.start_core.bacteria.input");

                StarTBacteriaBehaviour inputBehaviour = StarTBacteriaBehaviour.getBacteriaBehaviour(bacteriaInput);
                FluidType mainOutput = inputBehaviour.getBehaviourMainFluid();


                GTRecipe affinityRecipe = StarTRecipeTypes.BACTERIAL_HYDROCARBON_HARVESTER_RECIPES
                    .recipeBuilder(bacteria.getId().getPath().toString() + "_harvest")
                    .inputItems(bacteriaInput.copy())
                    .inputFluids(GTMaterials.Water.getFluid(1000).too)
                    .outputItems(bacteriaAffinityMutationOutput)
                    .duration(240)
                    .EUt(GTValues.V[GTValues.UV])
                    .buildRawRecipe();
            }
        );
    }
}
