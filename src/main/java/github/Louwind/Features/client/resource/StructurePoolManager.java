package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.FeatureGsons;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;

import static net.minecraft.util.registry.BuiltinRegistries.STRUCTURE_POOL;

public class StructurePoolManager extends JsonReloadListener<StructurePool> {

    private static final Gson GSON = FeatureGsons.getProcessorGsonBuilder().create();

    public StructurePoolManager() {
        super(GSON, StructurePool.class, STRUCTURE_POOL, "pools");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:pools");
    }

}
