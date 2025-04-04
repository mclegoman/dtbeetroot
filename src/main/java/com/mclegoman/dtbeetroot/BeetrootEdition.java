/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot;

import com.mclegoman.dtbeetroot.items.ItemRegistry;
import com.mclegoman.dtbeetroot.mine_effects.MineEffectRegistry;
import com.mclegoman.dtbeetroot.player_unlocks.PlayerUnlockRegistry;
import net.fabricmc.api.ModInitializer;

public class BeetrootEdition implements ModInitializer {
	public static final String modId = "dtbeetroot";
	@Override
	public void onInitialize() {
		ItemRegistry.init();
		PlayerUnlockRegistry.init();
		MineEffectRegistry.init();
	}
}