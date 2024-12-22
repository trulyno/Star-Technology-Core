package com.startechnology.start_core.item;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.item.DataItemBehavior;
import com.tterrag.registrate.util.entry.ItemEntry;

import static com.startechnology.start_core.StarTCore.START_REGISTRATE;

public class StarTItems {
    public static final ItemEntry<ComponentItem> TOOL_DATA_DNA_DISK = START_REGISTRATE.item("data_dna_disk", ComponentItem::create)
        .lang("Data DNA Disk")
        .onRegister((item) -> item.attachComponents(new DataItemBehavior(true)))
        .register();

    public static void init() {

    }
}
