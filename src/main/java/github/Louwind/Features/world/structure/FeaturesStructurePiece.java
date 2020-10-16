package github.Louwind.Features.world.structure;

import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.context.FeatureContextBuilder;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.metadata.FeatureMetadata;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Random;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;
import static github.Louwind.Features.impl.init.FeatureContextProviders.METADATA;
import static github.Louwind.Features.registry.FeaturesRegistry.FEATURE_METADATA;

public class FeaturesStructurePiece extends PoolStructurePiece {

    private BlockRotation rotation;

    public FeaturesStructurePiece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
        super(structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);

        this.rotation = blockRotation;
    }

    public FeaturesStructurePiece(StructureManager manager, CompoundTag tag) {
        super(manager, tag);

        this.rotation = BlockRotation.valueOf(tag.getString("rotation"));
    }

    @Override
    public boolean method_27236(StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox blockBox, BlockPos blockPos, boolean keepJigsaws) {
        ServerWorld world = structureWorldAccess.toServerWorld();
        StructureManager manager = world.getStructureManager();

        if(this.poolElement.generate(manager, structureWorldAccess, structureAccessor, chunkGenerator, this.pos, blockPos, this.rotation, blockBox, random, keepJigsaws)) {
            StructureManager structureManager = world.getStructureManager();

            if(this.poolElement instanceof SinglePoolElement) {
                SinglePoolElement singlePoolElement = (SinglePoolElement) this.poolElement;
                List<Structure.StructureBlockInfo> structureBlockInfos = singlePoolElement.getDataStructureBlocks(structureManager, blockPos, this.rotation, true);

                for (Structure.StructureBlockInfo structureBlockInfo : structureBlockInfos) {
                    Identifier id = new Identifier(structureBlockInfo.tag.getString("metadata"));

                    if(FEATURE_METADATA.containsId(id)) {
                        FeatureMetadata metadata = FEATURE_METADATA.get(id);

                        try {
                            FeatureContext context = new FeatureContextBuilder()
                                    .put(BLOCK_INFO, structureBlockInfo)
                                    .put(POS, pos)
                                    .put(RANDOM, random)
                                    .put(ROTATION, rotation)
                                    .put(WORLD, world)
                                    .build(METADATA);

                            metadata.accept(context);

                            List<FeatureFunction> functions = metadata.getFunctions();

                            for (FeatureFunction function: functions)
                                function.accept(context);

                        } catch (IllegalAccessException e) {
                            LogManager.getLogger().warn(e);
                        }
                    }

                }

                return true;
            }

        }

        return false;
    }

    @Override
    public void setOrientation(Direction orientation) {

        if (orientation == null) {
            this.rotation = BlockRotation.NONE;
        } else {
            switch(orientation) {
                case SOUTH:
                    this.rotation = BlockRotation.CLOCKWISE_180;
                    break;
                case WEST:
                    this.rotation = BlockRotation.COUNTERCLOCKWISE_90;
                    break;
                case EAST:
                    this.rotation = BlockRotation.CLOCKWISE_90;
                    break;
                default:
                    this.rotation = BlockRotation.NONE;
            }
        }

    }

    @Override
    protected void toNbt(CompoundTag tag) {
        super.toNbt(tag);

        tag.putString("rotation", this.rotation.name());
    }

    public String toString() {
        return String.format("<%s | %s | %s | %s>", this.getClass().getSimpleName(), this.pos, this.rotation, this.poolElement);
    }

    @Override
    public BlockRotation getRotation() {
        return this.rotation;
    }

}