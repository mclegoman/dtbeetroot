/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class FoodComponents {
	public static final FoodProperties goldenBeetrootFood;
	public static final Consumable goldenBeetrootConsumable;
	static {
		goldenBeetrootFood = new FoodProperties.Builder().nutrition(1).saturationModifier(0.9F).alwaysEdible().build();
		goldenBeetrootConsumable = Consumables.defaultFood().onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.RESISTANCE, 320, 0))).build();
	}
}
