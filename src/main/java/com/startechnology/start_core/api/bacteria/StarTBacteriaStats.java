package com.startechnology.start_core.api.bacteria;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;


public class StarTBacteriaStats {
    public static final String BACTERIA_PRODUCTION_NBT_TAG = "bacteria_production";
    public static final String BACTERIA_METABOLISM_NBT_TAG = "bacteria_metabolism";
    public static final String BACTERIA_MUTABILITY_NBT_TAG = "bacteria_mutability";
    public static final Integer MAX_STAT_VALUE = 5;

    private Integer production;
    private Integer metabolism;
    private Integer mutability;

    public String getProductionPretty() {
        return StarTBacteriaStats.getPrettyStatHighBias(production);
    }

    public String getMetabolismPretty() {
        return StarTBacteriaStats.getPrettyStatLowBias(metabolism);
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

    public StarTBacteriaStats(Integer production, Integer metabolism, Integer mutability) {
        this.production = production;
        this.metabolism = metabolism;
        this.mutability = mutability;
    }

    public StarTBacteriaStats(CompoundTag bacteriaStatsCompound) {
        this.production = bacteriaStatsCompound.getInt(BACTERIA_PRODUCTION_NBT_TAG);
        this.metabolism = bacteriaStatsCompound.getInt(BACTERIA_METABOLISM_NBT_TAG);
        this.mutability = bacteriaStatsCompound.getInt(BACTERIA_MUTABILITY_NBT_TAG);
    }

    public CompoundTag toCompoundTag() {
        CompoundTag bacteriaStatsCompound = new CompoundTag();

        bacteriaStatsCompound.putInt(BACTERIA_PRODUCTION_NBT_TAG, this.production);
        bacteriaStatsCompound.putInt(BACTERIA_METABOLISM_NBT_TAG, this.metabolism);
        bacteriaStatsCompound.putInt(BACTERIA_MUTABILITY_NBT_TAG, this.mutability);

        return bacteriaStatsCompound;
    }

    public static StarTBacteriaStats randomStats() {
        // Generate random stats
        Level level = Minecraft.getInstance().level;
        if (level == null) {
            return new StarTBacteriaStats(1, 1, 1);
        }

        RandomSource random = level.random;

        Integer production = random.nextIntBetweenInclusive(1, MAX_STAT_VALUE);
        Integer metabolism = random.nextIntBetweenInclusive(1, MAX_STAT_VALUE);
        Integer mutability = random.nextIntBetweenInclusive(1, MAX_STAT_VALUE);

        return new StarTBacteriaStats(production, metabolism, mutability);
    }
}
