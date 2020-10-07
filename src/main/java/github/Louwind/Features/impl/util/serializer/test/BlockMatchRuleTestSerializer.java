package github.Louwind.Features.impl.util.serializer.test;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.JsonSerializer;

public class BlockMatchRuleTestSerializer implements JsonSerializer<BlockMatchRuleTest> {

    @Override
    public void toJson(JsonObject json, BlockMatchRuleTest object, JsonSerializationContext context) {

    }

    @Override
    public BlockMatchRuleTest fromJson(JsonObject json, JsonDeserializationContext context) {
        Block block = FeaturesJsonHelper.getBlock(json, "block");

        return new BlockMatchRuleTest(block);
    }

}
