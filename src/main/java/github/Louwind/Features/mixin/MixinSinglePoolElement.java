package github.Louwind.Features.mixin;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.metadata.FeatureMetadata;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.logging.log4j.LogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;
import static github.Louwind.Features.impl.init.FeatureContextParameters.WORLD;
import static github.Louwind.Features.impl.init.FeatureContextProviders.METADATA;
import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_METADATA;

@Mixin(SinglePoolElement.class)
public abstract class MixinSinglePoolElement {

    @Shadow public abstract List<Structure.StructureBlockInfo> getDataStructureBlocks(StructureManager structureManager, BlockPos blockPos, BlockRotation blockRotation, boolean mirroredAndRotated);

    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/Structure;place(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/structure/StructurePlacementData;Ljava/util/Random;I)Z"))
    private void place(StructureManager structureManager, StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, BlockPos blockPos, BlockPos blockPos2, BlockRotation blockRotation, BlockBox blockBox, Random random, boolean keepJigsaws, CallbackInfoReturnable<Boolean> cir) {
        List<Structure.StructureBlockInfo> structureBlockInfos = this.getDataStructureBlocks(structureManager, blockPos, blockRotation, true);

        for (Structure.StructureBlockInfo structureBlockInfo : structureBlockInfos) {
            CompoundTag tag = structureBlockInfo.tag;

            if(tag != null) {
                String string = tag.getString("metadata");
                Identifier id = Identifier.tryParse(string);

                if(id != null && FEATURE_METADATA.containsId(id)) {
                    FeatureMetadata metadata = FEATURE_METADATA.get(id);

                    try {
                        FeatureContext context = new FeatureContextBuilder()
                                .put(BLOCK_INFO, structureBlockInfo)
                                .put(MIRROR, BlockMirror.NONE)
                                .put(POS, blockPos)
                                .put(RANDOM, random)
                                .put(ROTATION, blockRotation)
                                .put(WORLD, structureWorldAccess)
                                .build(METADATA);

                        metadata.accept(context);

                        List<FeatureFunction> functions = metadata.getFunctions();

                        for (FeatureFunction function: functions) {

                            if(function.test(context))
                                function.accept(context);
                        }

                    } catch (IllegalArgumentException e) {
                        LogManager.getLogger().warn(e);
                    }

                }

            }

        }

    }

}
