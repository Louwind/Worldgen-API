package github.Louwind.Features.util.json.adapter.worldgen;

import com.google.gson.*;
import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.util.MetadataHandlerList;
import github.Louwind.Features.util.json.FeaturesJsonHelper;

import java.lang.reflect.Type;
import java.util.Arrays;

public class MetadataHandlerListAdapter implements JsonDeserializer<MetadataHandlerList>, JsonSerializer<MetadataHandlerList> {

    @Override
    public MetadataHandlerList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var metadataHandlers = FeaturesJsonHelper.getMetadataHandlers(json.getAsJsonObject(), context, "metadata");

        return new MetadataHandlerList(Arrays.asList(metadataHandlers));
    }

    @Override
    public JsonElement serialize(MetadataHandlerList src, Type typeOfSrc, JsonSerializationContext context) {
        var metadataHandlers = src.getList();
        var json = new JsonObject();

        if(!metadataHandlers.isEmpty())
            json.add("metadata", context.serialize(metadataHandlers.toArray(MetadataHandler[]::new)));
        else
            json.add("metadata", new JsonArray());

        return json;
    }

}