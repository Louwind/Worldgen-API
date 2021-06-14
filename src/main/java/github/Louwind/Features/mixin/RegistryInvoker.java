package github.Louwind.Features.mixin;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Registry.class)
public interface RegistryInvoker {

    @Invoker("createRegistryKey")
    static <T> RegistryKey<Registry<T>> createRegistryKey(String registryId) {
        throw new AssertionError();
    }

}
