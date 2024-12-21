package com.startechnology.start_core.recipes.categories;

import java.util.function.Consumer;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.startechnology.start_core.recipes.StarTRecipe;

import net.minecraft.data.recipes.FinishedRecipe;

public class AkreyriumLine implements StarTRecipe {

    @Override
    public void initRecipes(Consumer<FinishedRecipe> provider) {
        leptonicManifoldQuantiserRecipes(provider);
    }

    public void leptonicManifoldQuantiserRecipes(Consumer<FinishedRecipe> provider) {
        GTRecipeType LEPTONIC_MANIFOLD_QUANTISER_TYPE = GTRecipeTypes.get("leptonic_manifold_quantiser");

        // KubeJS Interop, get materials registered.
        Material lepton_sparse_akreyrium = GTCEuAPI.materialManager.getMaterial("gtceu:lepton_sparse_akreyrium");
        Material sparse_electron_akreyrium = GTCEuAPI.materialManager.getMaterial("gtceu:sparse_electron_akreyrium");
        Material sparse_muon_akreyrium = GTCEuAPI.materialManager.getMaterial("gtceu:sparse_muon_akreyrium");
        Material sparse_tau_akreyrium = GTCEuAPI.materialManager.getMaterial("gtceu:sparse_tau_akreyrium");

        // Add our recipe but in java
        LEPTONIC_MANIFOLD_QUANTISER_TYPE.recipeBuilder("akreyrium_quantising")
            .inputFluids(lepton_sparse_akreyrium.getFluid(1000))
            .chancedFluidOutputLogic(ChanceLogic.XOR)
            .chancedOutput(sparse_electron_akreyrium.getFluid(1000), 20_00, 0)
            .chancedOutput(sparse_muon_akreyrium.getFluid(1000), 40_00, 0)
            .chancedOutput(sparse_tau_akreyrium.getFluid(1000), 99_99, 0)
            .duration(1200)
            .EUt(2097152)
            .save(provider);
    }
}
