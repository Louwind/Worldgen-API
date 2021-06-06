package github.Louwind.Features.mixin;

import github.Louwind.Features.metadata.MetadataHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.registry.FeaturesRegistry.METADATA_HANDLER;

@Mixin(SinglePoolElement.class)
public abstract class MixinSinglePoolElement {

    @Shadow public abstract List<Structure.StructureBlockInfo> getDataStructureBlocks(StructureManager structureManager, BlockPos blockPos, BlockRotation blockRotation, boolean mirroredAndRotated);

    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/structure/Structure;place(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/structure/StructurePlacementData;Ljava/util/Random;I)Z"))
    private void place(StructureManager structureManager, StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, BlockPos blockPos, BlockPos blockPos2, BlockRotation blockRotation, BlockBox blockBox, Random random, boolean keepJigsaws, CallbackInfoReturnable<Boolean> cir) {
        List<Structure.StructureBlockInfo> structureBlockInfos = this.getDataStructureBlocks(structureManager, blockPos, blockRotation, true);

        for (Structure.StructureBlockInfo blockInfo : structureBlockInfos) {
            NbtCompound tag = blockInfo.nbt;

            if(tag != null) {
                String string = tag.getString("metadata");
                Identifier id = Identifier.tryParse(string);

                if(id != null && METADATA_HANDLER.containsId(id)) {
                    MetadataHandler metadata = METADATA_HANDLER.get(id);
                    ServerWorld world = structureWorldAccess.toServerWorld();

                    if(metadata != null)
                        metadata.handle(world, blockInfo, blockPos, blockRotation, random);

                }

            }

        }

    }

}
