package com.startechnology.start_core.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;
import com.startechnology.start_core.recipe.logic.BacteriaVatLogic;
import com.startechnology.start_core.recipe.logic.BacterialDormantAwakeningLogic;
import com.startechnology.start_core.recipe.logic.BacterialHydrocarbonHarvesterLogic;
import com.startechnology.start_core.recipe.logic.BacterialRunicMutatorLogic;

public class StarTRecipeTypes {
    public static final GTRecipeType BACTERIAL_BREEDING_VAT_RECIPES = GTRecipeTypes.register("bacterial_breeding_vat", GTRecipeTypes.MULTIBLOCK)
        .setMaxIOSize(1, 2, 2, 0)
        .setEUIO(IO.IN)
        .addCustomRecipeLogic(new BacteriaVatLogic())
        .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressTexture.FillDirection.LEFT_TO_RIGHT);
    
    public static final GTRecipeType BACTERIAL_RUNIC_MUTATOR_RECIPES = GTRecipeTypes.register("bacterial_runic_mutator", GTRecipeTypes.MULTIBLOCK)
        .setMaxIOSize(2, 1, 2, 0)
        .setEUIO(IO.IN)
        .addCustomRecipeLogic(new BacterialRunicMutatorLogic())
        .addCustomRecipeLogic(new BacterialDormantAwakeningLogic())
        .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final GTRecipeType BACTERIAL_HYDROCARBON_HARVESTER_RECIPES = GTRecipeTypes.register("bacterial_hydrocarbon_harvester", GTRecipeTypes.MULTIBLOCK)
        .setMaxIOSize(2, 0, 2, 4)
        .setEUIO(IO.IN)
        .addCustomRecipeLogic(new BacterialHydrocarbonHarvesterLogic())
        .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressTexture.FillDirection.LEFT_TO_RIGHT);

    public static final void init() {
        
    }
}
