/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.player_unlocks;

import com.mclegoman.dtbeetroot.BeetrootEdition;
import com.mclegoman.dtbeetroot.items.ItemRegistry;
import com.mclegoman.dtbeetroot.items.ItemTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.players.PlayerUnlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemExchangeValue;
import net.minecraft.world.level.UnlockCondition;

public class PlayerUnlockRegistry {
	public static Holder<PlayerUnlock> beetroot;
	public static Holder<PlayerUnlock> betterBeetroot;
	public static Holder<PlayerUnlock> goldenBeetroot;
	public static Holder<PlayerUnlock> betterGoldenBeetroot;
	public static void init() {}
	public static Holder<PlayerUnlock> register(PlayerUnlock unlock) {
		return Registry.registerForHolder(BuiltInRegistries.PLAYER_UNLOCK, ResourceLocation.bySeparator(unlock.key().replaceFirst("_", ":"), ':'), unlock);
	}
	public static String key(String key) {
		return BeetrootEdition.modId + "_" + key;
	}
	static {
		beetroot = register(PlayerUnlock.root(key("beetroot")).withIcon(Items.BEETROOT).onMineEnter((player) -> {
			ItemStack itemStack = Items.BEETROOT.getDefaultInstance();
			itemStack.set(DataComponents.EXCHANGE_VALUE, new ItemExchangeValue(0.0F));
			player.addOrDropItem(itemStack);
		}).withPrice(20).build());
		betterBeetroot = register(PlayerUnlock.child(key("better_beetroot"), beetroot).withIcon(() -> {
			ItemStack itemStack = Items.BEETROOT.getDefaultInstance();
			itemStack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
			return itemStack;
		}).onMineEnter((player) -> {
			ItemStack itemStack = Items.BEETROOT.getDefaultInstance();
			itemStack.setCount(itemStack.getMaxStackSize());
			itemStack.set(DataComponents.EXCHANGE_VALUE, new ItemExchangeValue(0.0F));
			player.addOrDropItem(itemStack);
		}).withPrice(50).disablesOtherUnlock(beetroot).withVisibility(PlayerUnlock.UnlockVisibility.INVISIBLE).becomesVisibleWhen(UnlockCondition.unlocked(beetroot)).build());
		goldenBeetroot = register(PlayerUnlock.child(key("golden_beetroot"), betterBeetroot).withIcon(ItemRegistry.goldenBeetroot).onMineEnter((player) -> {
			ItemStack itemStack = ItemRegistry.goldenBeetroot.getDefaultInstance();
			itemStack.setCount(itemStack.getMaxStackSize());
			itemStack.set(DataComponents.EXCHANGE_VALUE, new ItemExchangeValue(0.0F));
			player.addOrDropItem(itemStack);
		}).withPrice(100).disablesOtherUnlock(betterBeetroot).withVisibility(PlayerUnlock.UnlockVisibility.MYSTERY).becomesVisibleWhen(UnlockCondition.obtainedItem((mine, player, stack) -> stack.is(ItemRegistry.goldenBeetroot))).build());
		betterGoldenBeetroot = register(PlayerUnlock.child(key("better_golden_beetroot"), goldenBeetroot).withIcon(() -> {
			ItemStack itemStack = ItemRegistry.goldenBeetroot.getDefaultInstance();
			itemStack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
			return itemStack;
		}).onMineEnter((player) -> {
			for (int i = 0; i < 100; i++) {
				ItemStack itemStack = ItemRegistry.goldenBeetroot.getDefaultInstance();
				itemStack.setCount(itemStack.getMaxStackSize());
				itemStack.set(DataComponents.EXCHANGE_VALUE, new ItemExchangeValue(0.0F));
				player.addOrDropItem(itemStack);
			}
		}).withPrice(1000).disablesOtherUnlock(goldenBeetroot).withVisibility(PlayerUnlock.UnlockVisibility.INVISIBLE).becomesVisibleWhen(UnlockCondition.unlocked(goldenBeetroot)).build());

		Holder<PlayerUnlock> profitableBeetroot = register(PlayerUnlock.child(key("profitable_beetroot"), beetroot).withIcon(Items.BEETROOT).modifyExperienceGainForItem(ItemTags.beetroot, 2.0F).withPrice(20).withVisibility(PlayerUnlock.UnlockVisibility.INVISIBLE).becomesVisibleWhen(UnlockCondition.unlocked(beetroot)).build());
		for (int i = 2; i <= 20;) {
			float gain = 2.0F * i;
			int price = 20 * i;
			ItemStack itemStack = i > 9 ? ItemRegistry.goldenBeetroot.getDefaultInstance() : Items.BEETROOT.getDefaultInstance();
			if (i == 20) {
				itemStack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
				gain *= 2.0F;
				price *= 2;
			}
			profitableBeetroot = register(PlayerUnlock.child(key("profitable_beetroot_" + i), profitableBeetroot).withIcon(() -> itemStack).modifyExperienceGainForItem(ItemTags.beetroot, gain).withPrice(price).disablesOtherUnlock(profitableBeetroot).withVisibility(PlayerUnlock.UnlockVisibility.INVISIBLE).becomesVisibleWhen(UnlockCondition.unlocked(profitableBeetroot)).build());
			i++;
		}
	}
}
