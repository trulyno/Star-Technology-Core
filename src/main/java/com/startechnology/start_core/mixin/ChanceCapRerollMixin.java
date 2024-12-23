//package com.startechnology.start_core.mixin;
//
//import com.gregtechceu.gtceu.api.GTValues;
//import com.gregtechceu.gtceu.api.recipe.chance.logic.ChanceLogic;
//import com.gregtechceu.gtceu.api.recipe.content.Content;
//
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//import it.unimi.dsi.fastutil.objects.Object2IntMap;
//
//@Mixin(ChanceLogic.class)
//public class ChanceCapRerollMixin {
//    // We make a mixin to reroll fields in ChanceLogic on retrieval if the retrieved
//    // chance is somehow greater than the max
//    //
//    // Gregtech also supresses warnings.. So follow in their steps.
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    @Inject(method = "getCachedChance", at = @At("HEAD"), remap = false)
//    private static void onGetCachedChance(Content entry, Object2IntMap<?> cache, CallbackInfoReturnable<Integer> cir) {
//        if (cache != null && cache.containsKey(entry.content) && cache.getInt(entry.content) > entry.maxChance) {
//
//            ((Object2IntMap) cache).put(entry.content, GTValues.RNG.nextInt(entry.maxChance));
//        }
//    }
//}