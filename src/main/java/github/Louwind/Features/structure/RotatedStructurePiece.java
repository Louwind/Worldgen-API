package github.Louwind.Features.structure;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;

public class RotatedStructurePiece extends PoolStructurePiece {

	public RotatedStructurePiece(StructureManager manager, CompoundTag tag) {
		super(manager, tag);
	}

    public RotatedStructurePiece(StructureManager structureManager, StructurePoolElement structurePoolElement, BlockPos blockPos, int i, BlockRotation blockRotation, BlockBox blockBox) {
		super(structureManager, structurePoolElement, blockPos, i, blockRotation, blockBox);
	}

}
