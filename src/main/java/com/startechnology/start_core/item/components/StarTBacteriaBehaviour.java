package com.startechnology.start_core.item.components;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.item.component.IAddInformation;
import com.startechnology.start_core.api.bacteria.StarTBacteriaManager;
import com.startechnology.start_core.api.bacteria.StarTBacteriaStats;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
public class StarTBacteriaBehaviour implements IAddInformation {

    private List<Material> possibleBacteriaAffinities;

    public StarTBacteriaBehaviour(Material... materials) {
        this.possibleBacteriaAffinities = Arrays.asList(materials);
    }

    public MutableComponent prettyPossibleBacteriaAffinities() {
        List<Component> translatableAffinities = possibleBacteriaAffinities.stream()
            .map(
                material -> Component.translatable(
                    material.getFluid().getFluidType().getDescriptionId()
                ).withStyle(ChatFormatting.DARK_PURPLE)
            )
            .collect(Collectors.toList());

        return Component.translatable("behaviour.start_core.bacteria.possible_affinities", translatableAffinities.toArray());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents,
            TooltipFlag isAdvanced) {
        StarTBacteriaStats stats = StarTBacteriaManager.bacteriaStatsFromTag(stack);

        if (stats == null) {
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.no_stats"));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(this.prettyPossibleBacteriaAffinities());
        } else {
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stats_header"));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stat_production", stats.getProductionPretty()));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stat_metabolism", stats.getMetabolismPretty()));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stat_mutability", stats.getMutabilityPretty()));
            tooltipComponents.add(Component.literal(""));
            tooltipComponents.add(Component.translatable("behaviour.start_core.bacteria.stat_affinity", stats.getAffinityPretty().withStyle(ChatFormatting.DARK_PURPLE)));
        }
    }
    
}
