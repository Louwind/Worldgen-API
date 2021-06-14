package github.Louwind.worldgen.client.resource;

import com.google.gson.Gson;
import github.Louwind.worldgen.util.json.FeaturesGsons;
import github.Louwind.Reload.client.resource.SimpleJsonReloadListener;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;

import static net.minecraft.util.registry.BuiltinRegistries.STRUCTURE_PROCESSOR_LIST;

public class StructureProcessorListReloadListener extends SimpleJsonReloadListener<StructureProcessorList> {

    private static final Gson GSON = FeaturesGsons.getStructureProcessorListGsonBuilder().create();

    public StructureProcessorListReloadListener() {
        super(GSON, StructureProcessorList.class, (SimpleRegistry<StructureProcessorList>) STRUCTURE_PROCESSOR_LIST, "worldgen/processor_list");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("minecraft:processor_list");
    }

}
