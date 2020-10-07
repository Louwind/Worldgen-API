package github.Louwind.Features.pool.element;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;

import java.util.function.Function;
import java.util.function.Supplier;

public interface FeaturesPoolElementFunction<T extends StructurePoolElement> extends Supplier<Function<StructurePool.Projection, T>> {

    FeaturePoolElementType<T> getType();

}
