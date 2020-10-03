package github.Louwind.Features.pool;

import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import net.minecraft.structure.pool.StructurePool;

import java.util.List;

/**
 * Represents a {@link StructurePool} and all logic applicable to it,
 * like context provider, functions and properties.
 * */
public interface FeaturePool {

    List<FeatureEntry> getEntries();

    FeatureContextProvider getContextProvider();

    List<FeatureFunction> getFunctions();

    OptionalContextParameter<StructurePool> getStructurePool();

    FeaturePoolType getType();

}
