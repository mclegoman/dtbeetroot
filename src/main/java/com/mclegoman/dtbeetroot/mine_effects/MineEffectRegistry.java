/*
    Beetroot Edition
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtbeetroot
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtbeetroot.mine_effects;

import com.mclegoman.dtbeetroot.BeetrootEdition;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.players.PlayerUnlocks;
import net.minecraft.world.level.UnlockCondition;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.GridChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.mines.WorldEffect;
import net.minecraft.world.level.mines.WorldEffects;

public class MineEffectRegistry {
	public static final WorldEffect beetroot;
	public static final WorldEffect parkour;
	public static void init() {}
	public static WorldEffect register(WorldEffect worldEffect) {
		return Registry.register(BuiltInRegistries.WORLD_EFFECT, ResourceLocation.bySeparator(worldEffect.key().replaceFirst("_", ":"), ':'), worldEffect);
	}
	public static String key(String key) {
		return BeetrootEdition.modId + "_" + key;
	}
	private static ResourceKey<PlacedFeature> placedFeature(ResourceLocation rL) {
		return ResourceKey.create(Registries.PLACED_FEATURE, rL);
	}
	static {
		beetroot = register(WorldEffect.builder(key("beetroot")).modifyingWorldGen((worldGenBuilder) -> worldGenBuilder.changeBiomes(biomeModificationBuilder -> {
			biomeModificationBuilder.modifyGenerationSettings((builder) -> {
				builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, worldGenBuilder.getOrThrow(placedFeature(ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "beetroot_0"))));
				builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, worldGenBuilder.getOrThrow(placedFeature(ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "beetroot_1"))));
				builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, worldGenBuilder.getOrThrow(placedFeature(ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "beetroot_2"))));
				builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, worldGenBuilder.getOrThrow(placedFeature(ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "beetroot_3"))));
				builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, worldGenBuilder.getOrThrow(placedFeature(ResourceLocation.fromNamespaceAndPath(BeetrootEdition.modId, "beetroot"))));
			});
		})).withCustomIcon(key("beetroot")).inSet(WorldEffects.WORLD_TYPE).unlockedBy(UnlockCondition.blockBreak((mine, state, pos) -> state.is(Blocks.BEETROOTS))).build());

		parkour = register(WorldEffect.builder(key("parkour")).modifyingWorldGen((worldGenBuilder) -> {
			worldGenBuilder.withCustomChunkGenerator((provider, biomeSource, holder) -> new GridChunkGenerator(biomeSource, holder, 2, 1, 64, false));
		}).withCustomIcon("grid_world").inSet(WorldEffects.WORLD_TYPE).unlockedBy(UnlockCondition.unlocked(PlayerUnlocks.JUMPING_10)).build());
	}
}
