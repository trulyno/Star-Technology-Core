package com.startechnology.start_core.machine.fusion;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.IFusionCasingType;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.block.FusionCasingBlock;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.startechnology.start_core.block.fusion.StarTFusionBlocks;

import net.minecraft.world.level.block.Block;

public class AuxiliaryBoostedFusionReactor extends FusionReactorMachine {
    public AuxiliaryBoostedFusionReactor(IMachineBlockEntity holder, int tier) {
        super(holder, tier);
    }


    public static Block getCasingState(int tier) {
        return switch (tier) {
            case GTValues.UHV -> StarTFusionBlocks.AUXILIARY_BOOSTED_FUSION_CASING_MK1.get();
            default -> FusionReactorMachine.getCasingState(tier);
        };
    }

    public static Block getAuxiliaryCoilState(int tier) {
        return switch (tier) {
            case GTValues.UHV -> StarTFusionBlocks.AUXILIARY_FUSION_COIL_MK1.get();
            default -> StarTFusionBlocks.AUXILIARY_FUSION_COIL_MK1.get();
        };
    }

    public static IFusionCasingType getCasingType(int tier) {
        return switch (tier) {
            case GTValues.UHV -> StarTFusionCasings.AUXILIARY_BOOSTED_FUSION_CASING_MK1;
            default -> FusionReactorMachine.getCasingType(tier);
        };
    }
}