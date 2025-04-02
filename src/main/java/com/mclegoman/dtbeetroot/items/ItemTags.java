/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.items;

import com.mclegoman.dtbeetroot.BeetrootEdition;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTags {
	public static final TagKey<Item> beetroot = bind(ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "beetroot"));
	private static TagKey<Item> bind(ResourceLocation id) {
		return TagKey.create(Registries.ITEM, id);
	}
}
