package github.Louwind.Features.impl.util.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;

public class TagMatchRuleTestSerializer implements JsonSerializer<TagMatchRuleTest> {

    @Override
    public void toJson(JsonObject json, TagMatchRuleTest object, JsonSerializationContext context) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public TagMatchRuleTest fromJson(JsonObject json, JsonDeserializationContext context) {
        Identifier id = FeaturesJsonHelper.getIdentifier(json, "tag");
        Tag<Block> tagBlock = BlockTags.getRequiredTags()
                .stream()
                .filter(tag -> tag.getId().equals(id))
                .findFirst()
                .orElse(null);

        return new TagMatchRuleTest(tagBlock);
    }

}
