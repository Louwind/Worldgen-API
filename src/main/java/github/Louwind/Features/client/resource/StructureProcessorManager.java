package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import github.Louwind.Features.util.FeatureGsons;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;

import static net.minecraft.util.registry.BuiltinRegistries.STRUCTURE_PROCESSOR_LIST;

public class StructureProcessorManager extends JsonReloadListener<StructureProcessorList>  {

    private static final Gson GSON = FeatureGsons.getProcessorGsonBuilder().create();

    public StructureProcessorManager() {
        super(GSON, StructureProcessorList.class, STRUCTURE_PROCESSOR_LIST, "processors");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:processors");
    }

}
