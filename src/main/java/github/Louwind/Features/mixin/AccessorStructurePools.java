package github.Louwind.Features.mixin;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructurePools.class)
public interface AccessorStructurePools {

    @Accessor("INVALID")
    static StructurePool invalid() {
        throw new AssertionError();
    }

}
