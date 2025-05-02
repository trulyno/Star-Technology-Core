package com.startechnology.start_core.machine.dreamlink;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import static com.startechnology.start_core.StarTCore.START_REGISTRATE;
import dev.latvian.mods.kubejs.KubeJS;

public class StarTDreamLinkTransmissionTowers {
        public static final MultiblockMachineDefinition DREAM_LINK_NODE = START_REGISTRATE
            .multiblock("dream_link_node", (holder) -> new StarTDreamLinkTransmissionMachine(holder, 5))
            .langValue("Dream Link Node")
            .recipeType(GTRecipeTypes.DUMMY_RECIPES)
            .rotationState(RotationState.NON_Y_AXIS)
            .pattern(definition -> FactoryBlockPattern.start()
                .aisle("CA")
                .where("C", Predicates.controller(Predicates.blocks(definition.get())))
                .where("A", Predicates.abilities(PartAbility.INPUT_LASER))
                .build()
            )
            .workableCasingRenderer(KubeJS.id("block/casings/machine_casing_peek"),
                GTCEu.id("block/machines/distillery"), false)
            .register();

    public static void init() {}
}
