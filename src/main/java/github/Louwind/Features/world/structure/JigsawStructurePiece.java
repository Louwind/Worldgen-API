package github.Louwind.Features.world.structure;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import static net.minecraft.util.BlockRotation.*;

public class JigsawStructurePiece extends PoolStructurePiece {

    public JigsawStructurePiece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
        super(structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);
    }

    public JigsawStructurePiece(ServerWorld world, NbtCompound tag) {
        super(world, tag);
    }

    @Override
    public void setOrientation(Direction orientation) {

        if (orientation == null) {
            this.rotation = NONE;
        } else {
            switch (orientation) {
                case SOUTH -> this.rotation = CLOCKWISE_180;
                case WEST -> this.rotation = COUNTERCLOCKWISE_90;
                case EAST -> this.rotation = CLOCKWISE_90;
                default -> this.rotation = NONE;
            }

        }

    }

}