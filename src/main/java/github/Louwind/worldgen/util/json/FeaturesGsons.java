package github.Louwind.worldgen.util.json;

import com.google.gson.GsonBuilder;
import github.Louwind.worldgen.util.MetadataHandlerList;
import github.Louwind.worldgen.util.json.adapter.worldgen.MetadataHandlerListAdapter;

public class FeaturesGsons {

    public static GsonBuilder getMetadataHandlerListGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(MetadataHandlerList.class, new MetadataHandlerListAdapter());
    }

}