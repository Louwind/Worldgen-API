package github.Louwind.worldgen.impl.processor;

import com.mojang.serialization.Codec;
import github.Louwind.worldgen.impl.init.StructureProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class IgnoreSolidBlocksStructureProcessor extends StructureProcessor {

    public static final Codec<IgnoreSolidBlocksStructureProcessor> CODEC = Codec.unit(new IgnoreSolidBlocksStructureProcessor());

    @Nullable
    @Override
    public Structure.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        BlockState state = world.getBlockState(structureBlockInfo2.pos);

        return state.isSolidBlock(world, structureBlockInfo2.pos) ? null : structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return StructureProcessors.IGNORE_SOLID_BLOCKS_PROCESSOR;
    }

}
