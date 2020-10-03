package github.Louwind.Features.world.structure;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

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

        return this.poolElement.generate(manager, structureWorldAccess, structureAccessor, chunkGenerator, this.pos, blockPos, this.rotation, blockBox, random, keepJigsaws);
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