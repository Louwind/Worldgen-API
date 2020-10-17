package github.Louwind.Features.mixin;

import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.pool.SinglePoolElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SinglePoolElement.class)
public interface InvokerSinglePoolElement {

    @Invoker("method_27233")
    Structure getStructure(StructureManager structureManager);

}
