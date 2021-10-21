package github.Louwind.worldgen.core.structure.processor;

import com.mojang.serialization.Codec;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import static github.Louwind.worldgen.core.init.WGStructureProcessors.IGNORE_SOLID_BLOCKS_PROCESSOR;

public class IgnoreSolidBlocksStructureProcessor extends StructureProcessor {

    public static final Codec<IgnoreSolidBlocksStructureProcessor> CODEC = Codec.unit(new IgnoreSolidBlocksStructureProcessor());

    @Nullable
    @Override
    public Structure.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        var state = world.getBlockState(structureBlockInfo2.pos);

        return state.isSolidBlock(world, structureBlockInfo2.pos) ? null : structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return IGNORE_SOLID_BLOCKS_PROCESSOR;
    }

}
