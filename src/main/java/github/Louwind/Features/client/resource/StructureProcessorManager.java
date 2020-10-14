package github.Louwind.Features.client.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import github.Louwind.Features.util.FeatureGsons;
import net.minecraft.resource.ResourceManager;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.BuiltinRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static net.minecraft.util.registry.BuiltinRegistries.STRUCTURE_PROCESSOR_LIST;

public class StructureProcessorManager extends JsonReloadListener  {

    private static final Gson GSON = FeatureGsons.getProcessorGsonBuilder().create();
    private static final Logger LOGGER = LogManager.getLogger();

    public StructureProcessorManager() {
        super(GSON, "processors");
    }

    @Override
    public Identifier getFabricId() {
        return new Identifier("features:processors");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> loader, ResourceManager manager, Profiler profiler) {

        loader.forEach((id, jsonElement) -> {

            try {
                StructureProcessorList structureProcessorList = GSON.fromJson(jsonElement, StructureProcessorList.class);

                if(!STRUCTURE_PROCESSOR_LIST.containsId(id))
                    BuiltinRegistries.add(STRUCTURE_PROCESSOR_LIST, id, structureProcessorList);
                else {
                    int rawId = STRUCTURE_PROCESSOR_LIST.getRawId(structureProcessorList);

                    STRUCTURE_PROCESSOR_LIST.getKey(structureProcessorList).ifPresent(key -> {
                        BuiltinRegistries.set(STRUCTURE_PROCESSOR_LIST, rawId, key, structureProcessorList);
                    });
                }

            } catch (Exception exception) {
                LOGGER.error("Couldn't parse processor list {}", id, exception);
            }

        });

    }

}
