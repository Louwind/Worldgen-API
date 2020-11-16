package github.Louwind.Features.impl.procesor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import github.Louwind.Features.impl.init.FeatureProcessors;
import net.minecraft.block.BlockState;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class IgnoreSolidBlocksStructureProcessor extends StructureProcessor {

    public static final Codec<IgnoreSolidBlocksStructureProcessor> CODEC = Codec.unit(new IgnoreSolidBlocksStructureProcessor());

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        BlockState state = worldView.getBlockState(structureBlockInfo2.pos);

        return state.isSolidBlock(worldView, structureBlockInfo2.pos) ? null : structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return FeatureProcessors.IGNORE_SOLID_BLOCKS_PROCESSOR;
    }

    public static class Serializer implements JsonSerializer<IgnoreSolidBlocksStructureProcessor> {

        @Override
        public void toJson(JsonObject json, IgnoreSolidBlocksStructureProcessor object, JsonSerializationContext context) {

        }

        @Override
        public IgnoreSolidBlocksStructureProcessor fromJson(JsonObject json, JsonDeserializationContext context) {
            return new IgnoreSolidBlocksStructureProcessor();
        }

    }

}
