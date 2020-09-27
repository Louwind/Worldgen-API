package github.Louwind.Features.impl.util.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.BlockStateMatchRuleTest;
import net.minecraft.util.JsonSerializer;

public class BlockstateMatchRuleTestSerializer implements JsonSerializer<BlockStateMatchRuleTest> {

    @Override
    public void toJson(JsonObject json, BlockStateMatchRuleTest object, JsonSerializationContext context) {

    }

    @Override
    public BlockStateMatchRuleTest fromJson(JsonObject json, JsonDeserializationContext context) {
        BlockState blockState = FeaturesJsonHelper.getBlockState(json, "blockstate");

        return new BlockStateMatchRuleTest(blockState);
    }

}
