package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.MetadataHandlerList;
import github.Louwind.Features.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.util.Identifier;

import static github.Louwind.Features.registry.Registries.METADATA_HANDLER_LIST;

public class MetadataHandlerListReloadListener extends SimpleJsonReloadListener<MetadataHandlerList> {

    private static final Gson GSON = FeaturesGsons.getMetadataGsonBuilder().create();

    public MetadataHandlerListReloadListener() {
        super(GSON, MetadataHandlerList.class, METADATA_HANDLER_LIST, "worldgen/metadata");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:metadata");
    }

}
