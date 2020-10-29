package github.Louwind.Features.util.deserializer;

import com.google.gson.*;
import github.Louwind.Features.util.FeaturesJsonHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.biome.SpawnSettings;

import java.lang.reflect.Type;

public class SpawnEntryDeserializer implements JsonDeserializer<SpawnSettings.SpawnEntry> {

    @Override
    public SpawnSettings.SpawnEntry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        EntityType<?> entityType = FeaturesJsonHelper.getEntityType(object, "entity_type");
        int weight = JsonHelper.getInt(object, "weight",  1);

        JsonObject range = JsonHelper.getObject(object, "range");

        int min = JsonHelper.getInt(range, "min",  1);
        int max = JsonHelper.getInt(range, "max",  1);

        return new SpawnSettings.SpawnEntry(entityType, weight, min, max);
    }

}
