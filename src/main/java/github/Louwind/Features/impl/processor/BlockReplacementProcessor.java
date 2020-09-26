package github.Louwind.Features.impl.processor;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.mojang.serialization.Codec;
import github.Louwind.Features.impl.init.FeatureProcessors;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.WorldView;

public class BlockReplacementProcessor extends StructureProcessor {

    public static final BlockReplacementProcessor INSTANCE = new BlockReplacementProcessor();
    public static final Codec<BlockReplacementProcessor> CODEC = Codec.unit(() -> INSTANCE);

    private final Block block;
    private final Block replacement;

    public BlockReplacementProcessor(Block block, Block replacement) {
        this.block = block;
        this.replacement = replacement;
    }

    public BlockReplacementProcessor() {
        this.block = null;
        this.replacement = null;
    }

    @Override
    public Structure.StructureBlockInfo process(WorldView worldView, BlockPos pos, BlockPos blockPos, Structure.StructureBlockInfo structureBlockInfo, Structure.StructureBlockInfo structureBlockInfo2, StructurePlacementData structurePlacementData) {
        BlockState state = structureBlockInfo2.state;

        if (this.block == state.getBlock() && this.replacement != null)
            return new Structure.StructureBlockInfo(structureBlockInfo2.pos, this.replacement.getDefaultState(), null);

        return structureBlockInfo2;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return FeatureProcessors.BLOCK_REPLACEMENT_PROCESSOR;
    }

    public static class Serializer implements JsonSerializer<BlockReplacementProcessor> {

        @Override
        public void toJson(JsonObject json, BlockReplacementProcessor object, JsonSerializationContext context) {
            Identifier blockId = Registry.BLOCK.getId(object.block);
            Identifier replacementId = Registry.BLOCK.getId(object.replacement);

            json.addProperty("processor", "features:replace");
            json.addProperty("block", blockId.toString());
            json.addProperty("replacement", replacementId.toString());
        }

        @Override
        public BlockReplacementProcessor fromJson(JsonObject json, JsonDeserializationContext context) {
            Block block = FeaturesJsonHelper.getBlock(json, "block");
            Block replacement = FeaturesJsonHelper.getBlock(json, "replacement");

            return new BlockReplacementProcessor(block, replacement);
        }

    }

}
