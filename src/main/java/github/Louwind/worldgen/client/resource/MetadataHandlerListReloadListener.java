package github.Louwind.worldgen.client.resource;

import com.google.gson.Gson;
import github.Louwind.worldgen.util.MetadataHandlerList;
import github.Louwind.worldgen.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;

import static github.Louwind.worldgen.registry.Registries.METADATA_HANDLER_LIST;

public class MetadataHandlerListReloadListener extends SimpleJsonReloadListener<MetadataHandlerList> {

    private static final Gson GSON = FeaturesGsons.getMetadataHandlerListGsonBuilder().create();

    public MetadataHandlerListReloadListener() {
        super(GSON, MetadataHandlerList.class, METADATA_HANDLER_LIST, "worldgen/metadata");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:metadata");
    }

}
