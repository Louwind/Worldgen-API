package github.Louwind.Features.impl.pool_element;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.Louwind.Features.impl.init.StructurePoolElements;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElementType;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;

import java.util.function.Supplier;

public class NoUpdateNeighborsPoolElement extends SinglePoolElement {

    public static final Codec<NoUpdateNeighborsPoolElement> CODEC = RecordCodecBuilder.create(instance -> instance.group(method_28882(), method_28880(), method_28883()).apply(instance, NoUpdateNeighborsPoolElement::new));

    public NoUpdateNeighborsPoolElement(Either<Identifier, Structure> either, Supplier<StructureProcessorList> supplier, StructurePool.Projection projection) {
        super(either, supplier, projection);
    }

    @Override
    protected StructurePlacementData createPlacementData(BlockRotation blockRotation, BlockBox blockBox, boolean keepJigsaws) {
        StructurePlacementData placementData = super.createPlacementData(blockRotation, blockBox, keepJigsaws);

        placementData.setUpdateNeighbors(false);

        return placementData;
    }

    @Override
    public StructurePoolElementType<?> getType() {
        return StructurePoolElements.NO_UPDATE_NEIGHBORS;
    }

}