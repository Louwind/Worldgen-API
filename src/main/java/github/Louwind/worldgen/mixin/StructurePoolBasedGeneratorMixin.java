package github.Louwind.worldgen.mixin;

import github.Louwind.worldgen.impl.worldgen.feature.JigsawFeatureConfig;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(StructurePoolBasedGenerator.class)
public class StructurePoolBasedGeneratorMixin {

    private static StructurePoolFeatureConfig config;

    @Inject(method = "generate", at = @At("HEAD"))
    private static void b(DynamicRegistryManager dynamicRegistryManager, StructurePoolFeatureConfig structurePoolFeatureConfig, StructurePoolBasedGenerator.PieceFactory pieceFactory, ChunkGenerator chunkGenerator, StructureManager structureManager, BlockPos blockPos, StructurePiecesHolder structurePiecesHolder, Random random, boolean bl, boolean bl2, HeightLimitView heightLimitView, CallbackInfo ci) {
        config = structurePoolFeatureConfig;
    }

    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/BlockRotation;random(Ljava/util/Random;)Lnet/minecraft/util/BlockRotation;"))
    private static BlockRotation a(Random random) {

        if(config instanceof JigsawFeatureConfig) {
            JigsawFeatureConfig jigsawConfig = (JigsawFeatureConfig) config;

            return jigsawConfig.getRotationChooser().random(random);
        }

        return BlockRotation.random(random);
    }

}
