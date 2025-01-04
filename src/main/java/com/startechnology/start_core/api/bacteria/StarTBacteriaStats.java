package com.startechnology.start_core.api.bacteria;
import org.apache.commons.lang3.StringUtils;

import com.gregtechceu.gtceu.GTCEu;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;


public class StarTBacteriaStats {
    public static final String BACTERIA_PRODUCTION_NBT_TAG = "bacteria_production";
    public static final String BACTERIA_METABOLISM_NBT_TAG = "bacteria_metabolism";
    public static final String BACTERIA_MUTABILITY_NBT_TAG = "bacteria_mutability";
    public static final String BACTERIA_AFFINITY_NBT_TAG = "bacteria_affinity";
    public static final Integer MAX_STAT_VALUE = 5;

    private Integer production;
    private Integer metabolism;
    private Integer mutability;
    private Fluid affinity;

    public Integer getProduction() {
        return production;
    }

    public Integer getMetabolism() {
        return metabolism;
    }

    public Integer getMutability() {
        return mutability;
    }

    public Fluid getAffinity() {
        return affinity;
    }

    public String getProductionPretty() {
        return StarTBacteriaStats.getPrettyStatHighBias(production);
    }

    public String getMetabolismPretty() {
        return StarTBacteriaStats.getPrettyStatLowBias(metabolism);
    }

    public MutableComponent getAffinityPretty() {
        if (affinity == null) {
            return Component.translatable("behaviour.start_core.bacteria.affinity_none");
        }
        return Component.translatable(affinity.getFluidType().getDescriptionId());
    }

    public String getMutabilityPretty() {
        return String.format("§f%s§r", StarTBacteriaStats.getStatRectangleForm(mutability));
    }

    public static String getPrettyStatHighBias(Integer stat) {
        String colourCode = switch (stat) {
            case 1 -> "§4";
            case 2 -> "§c";
            case 3 -> "§e";
            case 4 -> "§2";
            case 5 -> "§a";
            default -> " §e";
        };

        return String.format("%s%s§r", colourCode, StarTBacteriaStats.getStatRectangleForm(stat));
    }


    public static String getPrettyStatLowBias(Integer stat) {
        String colourCode = switch (stat) {
            case 5 -> "§4";
            case 4 -> "§c";
            case 3 -> "§e";
            case 2 -> "§2";
            case 1 -> "§a";
            default -> " §e";
        };

        return String.format("%s%s§r", colourCode, StarTBacteriaStats.getStatRectangleForm(stat));
    }

    public static String getStatRectangleForm(Integer stat) {
        return StringUtils.repeat('■', stat) + StringUtils.repeat('□', MAX_STAT_VALUE - stat);
    }

    public StarTBacteriaStats(Integer production, Integer metabolism, Integer mutability, Fluid affinity) {
        this.production = production;
        this.metabolism = metabolism;
        this.mutability = mutability;
        this.affinity = affinity;
    }

    public StarTBacteriaStats(CompoundTag bacteriaStatsCompound) {
        this.production = bacteriaStatsCompound.getInt(BACTERIA_PRODUCTION_NBT_TAG);
        this.metabolism = bacteriaStatsCompound.getInt(BACTERIA_METABOLISM_NBT_TAG);
        this.mutability = bacteriaStatsCompound.getInt(BACTERIA_MUTABILITY_NBT_TAG);
        
        String bacteriaFluidAffinityString = bacteriaStatsCompound.getString(BACTERIA_AFFINITY_NBT_TAG);
        ResourceLocation bacteriaAffinityLocation = new ResourceLocation(GTCEu.MOD_ID, bacteriaFluidAffinityString);
        
        if (ForgeRegistries.FLUIDS.containsKey(bacteriaAffinityLocation)) {
            this.affinity = ForgeRegistries.FLUIDS.getValue(bacteriaAffinityLocation);
        }
    }

    public CompoundTag toCompoundTag() {
        CompoundTag bacteriaStatsCompound = new CompoundTag();

        bacteriaStatsCompound.putInt(BACTERIA_PRODUCTION_NBT_TAG, this.production);
        bacteriaStatsCompound.putInt(BACTERIA_METABOLISM_NBT_TAG, this.metabolism);
        bacteriaStatsCompound.putInt(BACTERIA_MUTABILITY_NBT_TAG, this.mutability);

        if (affinity != null) {
            bacteriaStatsCompound.putString(
                BACTERIA_AFFINITY_NBT_TAG, 
                ForgeRegistries.FLUIDS.getKey(affinity).getPath().toString()
            );
        }

        return bacteriaStatsCompound;
    }
}
