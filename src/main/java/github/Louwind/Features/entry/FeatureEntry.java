package github.Louwind.Features.entry;

import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.function.FeatureFunction;
import net.minecraft.util.Identifier;

import java.util.List;

public interface FeatureEntry {

    List<FeatureFunction> getFunctions();

    List<FeatureContextSetter> getSetters();

    Identifier getStructureId();

    FeatureEntryType getType();

}
