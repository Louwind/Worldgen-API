package github.Louwind.Features.util.deserializer;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.JsonSerializer;

import java.util.List;

public class BlockIgnoreProcessorSerializer implements JsonSerializer<BlockIgnoreStructureProcessor> {


    @Override
    public void toJson(JsonObject json, BlockIgnoreStructureProcessor object, JsonSerializationContext context) {

    }

    @Override
    public BlockIgnoreStructureProcessor fromJson(JsonObject json, JsonDeserializationContext context) {
        List<Block> blocks = FeaturesJsonHelper.getList(json, FeaturesJsonHelper::asBlock, "blocks");

        return new BlockIgnoreStructureProcessor(ImmutableList.copyOf(blocks));
    }

}
