package github.Louwind.Features.mixin;

import com.mojang.datafixers.util.Either;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SinglePoolElement.class)
public interface SinglePoolElementAccessor {

    @Accessor("field_24015")
    Either<Identifier, Structure> getStructure();

}
