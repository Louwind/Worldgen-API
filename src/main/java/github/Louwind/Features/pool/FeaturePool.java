package github.Louwind.Features.pool;

import github.Louwind.Features.context.parameter.OptionalContextParameter;
import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.properties.FeatureProperties;
import net.minecraft.structure.pool.StructurePool;

import java.util.List;

public interface FeaturePool {

    List<FeatureEntry> getEntries();

    List<FeatureFunction> getFunctions();

    FeatureProperties getProperties();

    List<FeatureContextSetter> getSetters();

    OptionalContextParameter<StructurePool> getStructurePool();

}
