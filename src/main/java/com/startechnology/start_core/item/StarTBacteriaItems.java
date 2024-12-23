package com.startechnology.start_core.item;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.IComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.startechnology.start_core.item.components.StarTBacteriaBehaviour;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import static com.startechnology.start_core.StarTCore.START_REGISTRATE;

public class StarTBacteriaItems {
    public static <T extends IComponentItem> NonNullConsumer<T> attach(IItemComponent components) {
        return item -> item.attachComponents(components);
    }

    public static final ItemEntry<ComponentItem> BACTERIA_PHENOL = START_REGISTRATE.item("bacteria_phenol", ComponentItem::create)
        .lang("Phenol Bacteria Culture")

        // Limit stack size to 1
        .properties(prop -> prop.stacksTo(1))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Creosote,
            GTMaterials.Benzene,
            GTMaterials.Toluene
        )))
        .register();

    public static void init() {
    }
}
