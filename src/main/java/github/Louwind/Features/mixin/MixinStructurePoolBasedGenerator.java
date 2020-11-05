package github.Louwind.Features.mixin;

import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.registry.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(StructurePoolBasedGenerator.class)
public class MixinStructurePoolBasedGenerator {

    @Redirect(method = "method_27230", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/DynamicRegistryManager;get(Lnet/minecraft/util/registry/RegistryKey;)Lnet/minecraft/util/registry/MutableRegistry;"))
    private static <E> MutableRegistry<E> get(DynamicRegistryManager dynamicRegistryManager, RegistryKey<? extends Registry<E>> key) {
        return (MutableRegistry<E>) BuiltinRegistries.STRUCTURE_POOL;
    }

}
