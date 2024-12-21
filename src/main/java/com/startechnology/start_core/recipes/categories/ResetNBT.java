package com.startechnology.start_core.recipes.categories;

import java.util.function.Consumer;

import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import com.startechnology.start_core.recipes.StarTRecipe;
import static com.startechnology.start_core.item.StarTItems.TOOL_DATA_DNA_DISK;

import net.minecraft.data.recipes.FinishedRecipe;

public class ResetNBT implements StarTRecipe {

    @Override
    public void initRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapelessNBTClearingRecipe(provider, "data_dna_disk_nbt", TOOL_DATA_DNA_DISK.asStack(), 
            TOOL_DATA_DNA_DISK.asStack()
        );
    }
}
