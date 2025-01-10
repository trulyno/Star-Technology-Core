package com.startechnology.start_core.machine.fusion;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.gregtechceu.gtceu.api.pattern.Predicates;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

public class StarTFusionMachines {
    public static String fusionTierString(int tier) {
        return switch (tier) {
            case GTValues.UHV -> "I";
            default -> "I";
        };
    }

    public static final MultiblockMachineDefinition[] AUXILIARY_BOOSTED_FUSION_REACTOR = GTMachineUtils.registerTieredMultis(
        "auxiliary_boosted_fusion_reactor", AuxiliaryBoostedFusionReactor::new, 
        (tier, builder) ->
            builder
            .rotationState(RotationState.ALL)
            .langValue("Auxiliary Boosted Fusion Reactor MK %s".formatted(fusionTierString(tier)))
            .recipeType(GTRecipeTypes.FUSION_RECIPES)
            .recipeModifiers(GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                    AuxiliaryBoostedFusionReactor::recipeModifier)
            .tooltips(
                Component.translatable("start_core.machine.auxiliary_boosted_fusion_reactor.line"),
                Component.translatable("gtceu.machine.fusion_reactor.capacity",
                        AuxiliaryBoostedFusionReactor.calculateEnergyStorageFactor(tier, 16) / 1000000L),
                Component.translatable("gtceu.machine.fusion_reactor.overclocking")
            )
            .appearanceBlock(() -> AuxiliaryBoostedFusionReactor.getCasingState(tier))
            .pattern((definition) -> { 
                var casing = Predicates.blocks(AuxiliaryBoostedFusionReactor.getCasingState(tier));
                return FactoryBlockPattern.start()
	                .aisle("A######################", "#######################", "###########B###########", "###########B###########", "###########B###########", "#######################", "#######################") 
	                .aisle("#######################", "###########B###########", "#######################", "#######################", "#######################", "###########B###########", "#######################") 
                    .aisle("###########B###########", "#######################", "#######################", "#########CDDDC#########", "#######################", "#######################", "###########B###########") 
                    .aisle("###########B###########", "#######################", "###B#####EAAAE#####B###", "###B###DD#####DD###B###", "###B#####EAAAE#####B###", "#######################", "###########B###########") 
                    .aisle("###########B###########", "####B#############B####", "#######AA#####AA#######", "#####DF##CDDDC##FD#####", "#######AA#####AA#######", "####B#############B####", "###########B###########") 
                    .aisle("#####B###########B#####", "###########B###########", "#####AA#########AA#####", "####DGGFD#####DFGGD####", "#####AA#########AA#####", "###########B###########", "#####B###########B#####") 
                    .aisle("######B#########B######", "#######################", "#####A#####B#####A#####", "####FGD####B####DGF####", "#####A#####B#####A#####", "#######################", "######B#########B######") 
                    .aisle("#######################", "#######B#######B#######", "####A#############A####", "###D#F###########F#D###", "####A#############A####", "#######B#######B#######", "#######################") 
                    .aisle("#######################", "#######################", "####A###B#####B###A####", "###D#D##B#####B##D#D###", "####A###B#####B###A####", "#######################", "#######################") 
                    .aisle("#######################", "#######################", "###E###############E###", "##C#C#############C#C##", "###E###############E###", "#######################", "#######################") 
                    .aisle("#######################", "#######################", "###A###############A###", "##D#D#############D#D##", "###A###############A###", "#######################", "#######################") 
                    .aisle("##BBB#############BBB##", "#B###B###########B###B#", "B##A##B#########B##A##B", "B#D#D#B#########B#D#D#B", "B##A##B#########B##A##B", "#B###B###########B###B#", "##BBB#############BBB##") 
                    .aisle("#######################", "#######################", "###A###############A###", "##D#D#############D#D##", "###A###############A###", "#######################", "#######################") 
                    .aisle("#######################", "#######################", "###E###############E###", "##C#C#############C#C##", "###E###############E###", "#######################", "#######################") 
                    .aisle("#######################", "#######################", "####A###B#####B###A####", "###D#D##B#####B##D#D###", "####A###B#####B###A####", "#######################", "#######################") 
                    .aisle("#######################", "#######B#######B#######", "####A#############A####", "###D#F###########F#D###", "####A#############A####", "#######B#######B#######", "#######################") 
                    .aisle("######B#########B######", "#######################", "#####A#####B#####A#####", "####FGD####B####DGF####", "#####A#####B#####A#####", "#######################", "######B#########B######") 
                    .aisle("#####B###########B#####", "###########B###########", "#####AA#########AA#####", "####DGGFD#####DFGGD####", "#####AA#########AA#####", "###########B###########", "#####B###########B#####") 
                    .aisle("###########B###########", "####B#############B####", "#######AA#####AA#######", "#####DF##CDDDC##FD#####", "#######AA#####AA#######", "####B#############B####", "###########B###########") 
                    .aisle("###########B###########", "#######################", "###B#####EAAAE#####B###", "###B###DD#####DD###B###", "###B#####EAAAE#####B###", "#######################", "###########B###########") 
                    .aisle("###########B###########", "#######################", "#######################", "#########CDSDC#########", "#######################", "#######################", "###########B###########") 
                    .aisle("#######################", "###########B###########", "#######################", "#######################", "#######################", "###########B###########", "#######################") 
                    .aisle("#######################", "#######################", "###########B###########", "###########B###########", "###########B###########", "#######################", "######################A") 
                    .where('S', Predicates.controller(Predicates.blocks(definition.get())))
                    .where("A", casing)
                    .where("#", Predicates.any())
                    .where("B", Predicates.blocks(AuxiliaryBoostedFusionReactor.getAuxiliaryCoilState(tier)))
                    .where("C", casing.or(Predicates.abilities(PartAbility.EXPORT_FLUIDS)))
                    .where("D", Predicates.blocks(GTBlocks.FUSION_GLASS.get()).or(casing))
                    .where("E", casing.or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(2)))
                    .where("G", Predicates.blocks(AuxiliaryBoostedFusionReactor.getCoilState(tier)))
                    .where("F", casing.or(
                        Predicates.blocks(PartAbility.INPUT_ENERGY.getBlockRange(tier, GTValues.UHV).toArray(Block[]::new))
                                .setMinGlobalLimited(1).setPreviewCount(16)))
                    .build();
            })
            .register(),
        GTValues.UHV);
        
    public static void init() {}
}
