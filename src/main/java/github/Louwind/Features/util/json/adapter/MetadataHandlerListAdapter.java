package github.Louwind.Features.util.json.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import github.Louwind.Features.util.MetadataHandlerList;
import github.Louwind.Features.util.json.FeaturesJsonHelper;

import java.lang.reflect.Type;
import java.util.Arrays;

public class MetadataHandlerListAdapter implements JsonDeserializer<MetadataHandlerList> {

    @Override
    public MetadataHandlerList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var metadataHandlers = FeaturesJsonHelper.getMetadataHandlers(json.getAsJsonObject(), context, "metadata");

        return new MetadataHandlerList(Arrays.asList(metadataHandlers));
    }

}