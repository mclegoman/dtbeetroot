/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.items;

import com.mclegoman.dtbeetroot.BeetrootEdition;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

public class ItemRegistry {
	public static final Item goldenBeetroot;
	public static void init() {}
	private static ResourceKey<Item> itemId(ResourceLocation resourceLocation) {
		return ResourceKey.create(Registries.ITEM, resourceLocation);
	}
	static {
		goldenBeetroot = Items.registerItem(itemId(ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "golden_beetroot")), Item::new, new Item.Properties().rarity(Rarity.RARE).food(FoodComponents.goldenBeetrootFood, FoodComponents.goldenBeetrootConsumable).experienceExchangeValue(0.04F));
	}
}
