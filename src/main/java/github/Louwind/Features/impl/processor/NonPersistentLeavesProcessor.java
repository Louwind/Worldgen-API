package github.Louwind.Features.impl.processor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import github.Louwind.Features.impl.FeatureProcessorTypes;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class NonPersistentLeavesProcessor extends StructureProcessor {

    public static final NonPersistentLeavesProcessor INSTANCE = new NonPersistentLeavesProcessor();
    public static final Codec<NonPersistentLeavesProcessor> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        BlockState state = structureBlockInfo2.state;

        if (state.getBlock() instanceof LeavesBlock)
            return new Structure.StructureBlockInfo(structureBlockInfo2.pos, state.with(LeavesBlock.PERSISTENT, false), structureBlockInfo2.tag);

        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return FeatureProcessorTypes.NON_PERSISTENT_LEAVES;
    }

    public static class Serializer implements JsonSerializer<NonPersistentLeavesProcessor> {

        @Override
        public void toJson(JsonObject json, NonPersistentLeavesProcessor object, JsonSerializationContext context) {

        }

        @Override
        public NonPersistentLeavesProcessor fromJson(JsonObject json, JsonDeserializationContext context) {
            return new NonPersistentLeavesProcessor();
        }

    }

}
