package github.Louwind.worldgen.mixin;

import github.Louwind.worldgen.core.event.structure.pool.StructurePoolCallback;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructurePools.class)
public class MixinStructurePools {

    @Inject(method = "register", at = @At("HEAD"))
    private static void register(StructurePool pool, CallbackInfoReturnable<StructurePool> cir) {
        var accessor = (AccessorStructurePool) pool;

        var elementCounts = accessor.getElementCounts();
        var elements = accessor.getElements();

        StructurePoolCallback.event(pool).invoker().append(accessor, elements, elementCounts);
        StructurePoolCallback.EVENT.invoker().append(accessor, elements, elementCounts);
    }

}
