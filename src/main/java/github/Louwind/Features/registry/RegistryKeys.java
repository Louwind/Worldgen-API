package github.Louwind.Features.registry;

import github.Louwind.Features.metadata.MetadataHandler;
import github.Louwind.Features.mixin.RegistryInvoker;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class RegistryKeys {

    public static final RegistryKey<Registry<MetadataHandler>> METADATA_KEY = RegistryInvoker.createRegistryKey("worldgen/metadata");

}
