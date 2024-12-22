package com.startechnology.start_core.item.components;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.startechnology.start_core.api.bacteria.StarTBacteriaManager;
import com.startechnology.start_core.api.bacteria.StarTBacteriaStats;
import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class StarTBacteriaBehaviour implements IAddInformation {

    private List<ItemEntry<ComponentItem>> bacteriaAffinities = new ArrayList<>();

    public StarTBacteriaBehaviour() {
    }

    private List<Component> affinitiesToString() {
        return bacteriaAffinities.stream().map(
            (affinity) -> Component.translatable("behaviour.start_core.affinity", Component.translatable(affinity.getId().toString()))
        ).collect(Collectors.toList());
    }

    public void addAffinity(ItemEntry<ComponentItem> affinity) {
        bacteriaAffinities.add(affinity);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
            TooltipFlag isAdvanced) {
        StarTBacteriaStats stats = StarTBacteriaManager.bacteriaStatsFromTag(stack);

        if (stats == null) {
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.no_stats"));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.possible_affinities"));
            tooltipComponents.addAll(this.affinitiesToString());
        } else {
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stats_header"));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stat_production", stats.getProductionPretty()));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stat_metabolism", stats.getMetabolismPretty()));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stat_mutability", stats.getMutabilityPretty()));
        }
    }
    
}
