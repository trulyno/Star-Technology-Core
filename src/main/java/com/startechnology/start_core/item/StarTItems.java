package com.startechnology.start_core.item;

import static com.startechnology.start_core.StarTCore.START_REGISTRATE;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.IComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.item.DataItemBehavior;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import net.minecraft.network.chat.Component;

public class StarTItems {
    public static <T extends IComponentItem> NonNullConsumer<T> attach(IItemComponent components) {
        return item -> item.attachComponents(components);
    }

    public static final ItemEntry<ComponentItem> TOOL_DATA_DNA_DISK = START_REGISTRATE.item("data_dna_disk", ComponentItem::create)
        .lang("Data DNA Disk")
        .onRegister(attach(new DataItemBehavior(true)))
        .onRegister(attach(new TooltipBehavior(lines -> {
            lines.add(Component.translatable("item.start_core.data_dna_disk.tooltip"));
        })))
        .register();

    public static void init() {
        //StarTBacteriaItems.init();
    }
}
