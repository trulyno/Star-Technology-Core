package com.startechnology.start_core.item;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.IComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.startechnology.start_core.item.components.StarTBacteriaBehaviour;
import com.startechnology.start_core.item.components.StarTNBTTooltipsBehaviour;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import net.minecraft.network.chat.Component;

import static com.startechnology.start_core.StarTCore.START_REGISTRATE;

import java.util.Arrays;
import java.util.List;

public class StarTBacteriaItems {
    public static <T extends IComponentItem> NonNullConsumer<T> attach(IItemComponent components) {
        return item -> item.attachComponents(components);
    }

    public static final ItemEntry<ComponentItem> BACTERIA_DORMANT = START_REGISTRATE.item("bacteria_dormant", ComponentItem::create)
        .lang("Dormant Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTNBTTooltipsBehaviour()))
        .onRegister(attach(new TooltipBehavior(lines -> {
            lines.add(Component.translatable("item.start_core.bacteria_dormant.tooltip"));
        })))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_METHANE = START_REGISTRATE.item("bacteria_methane", ComponentItem::create)
        .lang("Methane Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Methane,
            GTMaterials.Butene,
            GTMaterials.Ethane,
            GTMaterials.Propane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_ETHANE = START_REGISTRATE.item("bacteria_ethane", ComponentItem::create)
        .lang("Ethane Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Ethane,
            GTMaterials.Methane,
            GTMaterials.Ethylene,
            GTMaterials.Propane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_PROPANE = START_REGISTRATE.item("bacteria_propane", ComponentItem::create)
        .lang("Propane Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Propane,
            GTMaterials.Methane,
            GTMaterials.Butadiene,
            GTMaterials.Ethane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_BUTANE = START_REGISTRATE.item("bacteria_butane", ComponentItem::create)
        .lang("Butane Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Butane,
            GTMaterials.Octane,
            GTMaterials.Ethane,
            GTMaterials.Methane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_OCTANE = START_REGISTRATE.item("bacteria_octane", ComponentItem::create)
        .lang("Octane Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Octane,
            GTMaterials.Methane,
            GTMaterials.Propane,
            GTMaterials.Butane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_ETHYLENE = START_REGISTRATE.item("bacteria_ethylene", ComponentItem::create)
        .lang("Ethylene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Ethylene,
            GTMaterials.Butadiene,
            GTMaterials.Toluene,
            GTMaterials.Benzene
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_PROPENE = START_REGISTRATE.item("bacteria_propene", ComponentItem::create)
        .lang("Propene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Propene,
            GTMaterials.Ethylene,
            GTMaterials.Propane,
            GTMaterials.Ethane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_BUTENE = START_REGISTRATE.item("bacteria_butene", ComponentItem::create)
        .lang("Butene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Butene,
            GTMaterials.Benzene,
            GTMaterials.Butadiene,
            GTMaterials.Ethylene
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_BUTADIENE = START_REGISTRATE.item("bacteria_butadiene", ComponentItem::create)
        .lang("Butadiene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Butadiene,
            GTMaterials.Propene,
            GTMaterials.Methane,
            GTMaterials.Ethylene
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_BENZENE = START_REGISTRATE.item("bacteria_benzene", ComponentItem::create)
        .lang("Benzene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Benzene,
            GTMaterials.Phenol,
            GTMaterials.Dimethylbenzene,
            GTMaterials.Creosote
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_TOLUENE = START_REGISTRATE.item("bacteria_toluene", ComponentItem::create)
        .lang("Toluene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Toluene,
            GTMaterials.Butadiene,
            GTMaterials.Butene,
            GTMaterials.Methane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_METHANOL = START_REGISTRATE.item("bacteria_methanol", ComponentItem::create)
        .lang("Methanol Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Methanol,
            GTMaterials.AceticAcid,
            GTMaterials.Ethanol,
            GTMaterials.Methane
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_ACETONE = START_REGISTRATE.item("bacteria_acetone", ComponentItem::create)
        .lang("Methanol Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Acetone,
            GTMaterials.Methanol,
            GTMaterials.MethylAcetate,
            GTMaterials.AceticAcid
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_ACETIC_ACID = START_REGISTRATE.item("bacteria_acetic_acid", ComponentItem::create)
        .lang("Acetic Acid Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.AceticAcid,
            GTMaterials.Ethanol,
            GTMaterials.Methanol,
            GTMaterials.Acetone
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_METHYL_ACETATE = START_REGISTRATE.item("bacteria_methyl_acetate", ComponentItem::create)
        .lang("Methyl Acetate Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.MethylAcetate,
            GTMaterials.Toluene,
            GTMaterials.Methanol,
            GTMaterials.Acetone
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_ETHANOL = START_REGISTRATE.item("bacteria_ethanol", ComponentItem::create)
        .lang("Ethanol Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Ethanol,
            GTMaterials.MethylAcetate,
            GTMaterials.Methanol,
            GTMaterials.Acetone
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_CREOSOTE = START_REGISTRATE.item("bacteria_creosote", ComponentItem::create)
        .lang("Creosote Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Creosote,
            GTMaterials.Toluene,
            GTMaterials.Phenol,
            GTMaterials.Dimethylbenzene
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_PHENOL = START_REGISTRATE.item("bacteria_phenol", ComponentItem::create)
        .lang("Phenol Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Phenol,
            GTMaterials.Benzene,
            GTMaterials.Creosote,
            GTMaterials.Toluene
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_DIMETHYLBENZENE = START_REGISTRATE.item("bacteria_dimethylbenzene", ComponentItem::create)
        .lang("Dimethylbenzene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Dimethylbenzene,
            GTMaterials.Phenol,
            GTMaterials.Toluene,
            GTMaterials.Creosote
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_ETHYLBENZENE = START_REGISTRATE.item("bacteria_ethylbenzene", ComponentItem::create)
        .lang("Ethylbenzene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Ethylbenzene,
            GTMaterials.Ethane,
            GTMaterials.Ethylene,
            GTMaterials.Benzene
        )))
        .register();

    public static final ItemEntry<ComponentItem> BACTERIA_NAPHTHALENE = START_REGISTRATE.item("bacteria_naphthalene", ComponentItem::create)
        .lang("Naphthalene Bacteria Culture")
        .properties(prop -> prop.stacksTo(16))
        .onRegister(attach(new StarTBacteriaBehaviour(
            GTMaterials.Naphthalene,
            GTMaterials.Ethylbenzene,
            GTMaterials.Creosote,
            GTMaterials.Phenol
        )))
        .register();

    public static List<ItemEntry<ComponentItem>> BACTERIA_ITEMS = Arrays.asList(
        StarTBacteriaItems.BACTERIA_METHANE,
        StarTBacteriaItems.BACTERIA_ETHANE,
        StarTBacteriaItems.BACTERIA_PROPANE,
        StarTBacteriaItems.BACTERIA_BUTANE,
        StarTBacteriaItems.BACTERIA_OCTANE,
        StarTBacteriaItems.BACTERIA_ETHYLENE,
        StarTBacteriaItems.BACTERIA_PROPENE,
        StarTBacteriaItems.BACTERIA_BUTENE,
        StarTBacteriaItems.BACTERIA_BUTADIENE,
        StarTBacteriaItems.BACTERIA_BENZENE,
        StarTBacteriaItems.BACTERIA_TOLUENE,
        StarTBacteriaItems.BACTERIA_METHANOL,
        StarTBacteriaItems.BACTERIA_ACETONE,
        StarTBacteriaItems.BACTERIA_ACETIC_ACID,
        StarTBacteriaItems.BACTERIA_METHYL_ACETATE,
        StarTBacteriaItems.BACTERIA_ETHANOL,
        StarTBacteriaItems.BACTERIA_CREOSOTE,
        StarTBacteriaItems.BACTERIA_PHENOL,
        StarTBacteriaItems.BACTERIA_DIMETHYLBENZENE,
        StarTBacteriaItems.BACTERIA_ETHYLBENZENE,
        StarTBacteriaItems.BACTERIA_NAPHTHALENE
    );

    public static void init() {
    }
}
