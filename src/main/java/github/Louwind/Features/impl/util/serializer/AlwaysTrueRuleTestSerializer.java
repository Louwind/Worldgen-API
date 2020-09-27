package github.Louwind.Features.impl.util.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.util.JsonSerializer;

public class AlwaysTrueRuleTestSerializer implements JsonSerializer<AlwaysTrueRuleTest> {

    @Override
    public void toJson(JsonObject json, AlwaysTrueRuleTest object, JsonSerializationContext context) {

    }

    @Override
    public AlwaysTrueRuleTest fromJson(JsonObject json, JsonDeserializationContext context) {
        return AlwaysTrueRuleTest.INSTANCE;
    }

}
