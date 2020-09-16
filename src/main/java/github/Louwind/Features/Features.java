package github.Louwind.Features;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class Features implements ModInitializer {

    public static final StructurePoolManager STRUCTURE_POOL_MANAGER = new StructurePoolManager();

    @Override
    public void onInitialize() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(STRUCTURE_POOL_MANAGER);
    }

}
