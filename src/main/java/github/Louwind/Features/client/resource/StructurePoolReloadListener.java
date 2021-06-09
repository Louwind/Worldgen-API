package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;

import static net.minecraft.util.registry.BuiltinRegistries.STRUCTURE_POOL;

public class StructurePoolReloadListener extends SimpleJsonReloadListener<StructurePool> {

    private static final Gson GSON = FeaturesGsons.getStructurePoolGsonBuilder().create();

    public StructurePoolReloadListener() {
        super(GSON, StructurePool.class, (SimpleRegistry<StructurePool>) STRUCTURE_POOL, "worldgen/template_pool");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:template_pool");
    }

}
