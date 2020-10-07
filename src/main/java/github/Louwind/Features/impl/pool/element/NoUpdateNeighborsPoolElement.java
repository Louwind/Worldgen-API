package github.Louwind.Features.impl.pool.element;

import com.mojang.datafixers.util.Either;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;

import java.util.function.Supplier;

public class NoUpdateNeighborsPoolElement extends SinglePoolElement {

    public NoUpdateNeighborsPoolElement(Either<Identifier, Structure> either, Supplier<StructureProcessorList> supplier, StructurePool.Projection projection) {
        super(either, supplier, projection);
    }

    @Override
    protected StructurePlacementData createPlacementData(BlockRotation blockRotation, BlockBox blockBox, boolean keepJigsaws) {
        StructurePlacementData placementData = super.createPlacementData(blockRotation, blockBox, keepJigsaws);

        placementData.setUpdateNeighbors(false);

        return placementData;
    }

}
