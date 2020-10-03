package github.Louwind.Features.mixin;

import net.minecraft.structure.StructurePiece;
import net.minecraft.util.BlockRotation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(StructurePiece.class)
public interface AccessorStructurePiece {

    @Accessor("rotation")
    BlockRotation getRotation();

}
