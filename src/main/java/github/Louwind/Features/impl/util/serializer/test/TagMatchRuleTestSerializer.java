package github.Louwind.Features.impl.util.serializer.test;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.block.Block;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ServerTagManagerHolder;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import static net.minecraft.util.registry.Registry.BLOCK_KEY;

public class TagMatchRuleTestSerializer implements JsonSerializer<TagMatchRuleTest> {

    @Override
    public void toJson(JsonObject json, TagMatchRuleTest object, JsonSerializationContext context) {

    }

    @Override
    public TagMatchRuleTest fromJson(JsonObject json, JsonDeserializationContext context) {
        var id = FeaturesJsonHelper.getIdentifier(json, "tag");
        var tag = ServerTagManagerHolder.getTagManager().getTag(BLOCK_KEY, id, i -> new JsonParseException("Can't find tag: " + i));

        return new TagMatchRuleTest(tag);
    }

}
