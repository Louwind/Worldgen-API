package github.Louwind.Features.entry;

import github.Louwind.Features.function.FeatureFunction;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * Represents an identified {@link net.minecraft.structure.Structure} and logic applicable to it
 * */
public interface FeatureEntry {

    List<FeatureFunction> getFunctions();

    Identifier getStructureId();

    FeatureEntryType getType();

}
