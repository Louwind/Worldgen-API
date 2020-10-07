package github.Louwind.Features.impl.util.serializer.test;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RandomBlockStateMatchRuleTest;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.JsonSerializer;

public class RandomBlockstateMatchRuleTestSerializer implements JsonSerializer<RandomBlockStateMatchRuleTest> {

    @Override
    public void toJson(JsonObject json, RandomBlockStateMatchRuleTest object, JsonSerializationContext context) {

    }

    @Override
    public RandomBlockStateMatchRuleTest fromJson(JsonObject json, JsonDeserializationContext context) {
        BlockState blockState = FeaturesJsonHelper.getBlockState(json, "blockstate");
        int probability = JsonHelper.getInt(json, "probability");

        return new RandomBlockStateMatchRuleTest(blockState, probability);
    }

}
