package github.Louwind.Features.mixin;

import github.Louwind.Features.config.PoolFeatureConfig;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.*;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(StructurePoolBasedGenerator.class)
public class MixinStructurePoolBasedGenerator {

    private static StructurePoolFeatureConfig config;

    @Inject(method = "method_30419", at = @At("HEAD"))
    private static void setConfig(DynamicRegistryManager dynamicRegistryManager, StructurePoolFeatureConfig structurePoolFeatureConfig, StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos blockPos, StructurePiecesHolder structurePiecesHolder, Random random, boolean bl, boolean bl2, HeightLimitView heightLimitView, CallbackInfo ci) {
        config = structurePoolFeatureConfig;
    }

    @Redirect(method = "method_30419", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/pool/StructurePoolBasedGenerator$PieceFactory;create(Lnet/minecraft/structure/StructureManager;Lnet/minecraft/structure/pool/StructurePoolElement;Lnet/minecraft/util/math/BlockPos;ILnet/minecraft/util/BlockRotation;Lnet/minecraft/util/math/BlockBox;)Lnet/minecraft/structure/PoolStructurePiece;"))
    private static PoolStructurePiece create(StructurePoolBasedGenerator.PieceFactory pieceFactory, StructureManager structureManager, StructurePoolElement poolElement, BlockPos pos, int i, BlockRotation rotation, BlockBox elementBounds) {

        if(config instanceof PoolFeatureConfig) {
            PoolFeatureConfig featureConfig = (PoolFeatureConfig) config;

            BlockRotation blockRotation = featureConfig.getRotation();
            BlockBox box = poolElement.getBoundingBox(structureManager, pos, blockRotation);

            return pieceFactory.create(structureManager, poolElement, pos, i, blockRotation, box);
        }

        return pieceFactory.create(structureManager, poolElement, pos, i, rotation, elementBounds);
    }

    @Redirect(method = "method_30419", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/DynamicRegistryManager;get(Lnet/minecraft/util/registry/RegistryKey;)Lnet/minecraft/util/registry/MutableRegistry;"))
    private static <E> MutableRegistry<E> get(DynamicRegistryManager dynamicRegistryManager, RegistryKey<? extends Registry<E>> key) {
        return (MutableRegistry<E>) BuiltinRegistries.STRUCTURE_POOL;
    }

}
