package github.Louwind.Features.pool;

import com.google.common.collect.Lists;
import github.Louwind.Features.context.provider.FeatureContextProvider;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.mixin.AccessorSinglePoolElement;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a {@link StructurePool} and all logic applicable to it,
 * like context provider, functions and properties.
 * */
public interface FeaturePool {

    List<FeatureEntry> getEntries();

    FeatureContextProvider getContextProvider();

    List<FeatureFunction> getFunctions();

    StructurePool getStructurePool();

    FeaturePoolType getType();

    default Optional<FeatureEntry> getEntry(StructurePoolElement poolElement) {

        if(poolElement instanceof SinglePoolElement) {
            SinglePoolElement singlePoolElement = (SinglePoolElement) poolElement;
            AccessorSinglePoolElement accessor = (AccessorSinglePoolElement) singlePoolElement;

            Optional<Identifier> optional = accessor.getStructure().left();

            if(optional.isPresent()) {
                Identifier id = optional.get();

                return this.getEntries()
                        .stream()
                        .filter(entry -> entry.getStructureId().equals(id))
                        .findFirst();
            }

        }

        return Optional.empty();
    }

    default List<FeatureFunction> getFunctions(StructurePoolElement poolElement) {
        Optional<FeatureEntry> entryOptional = this.getEntry(poolElement);

        if (entryOptional.isPresent()) {
            FeatureEntry entry = entryOptional.get();

            List<FeatureFunction> entryList = entry.getFunctions();
            List<FeatureFunction> functions = this.getFunctions();

            return Stream.concat(functions.stream(), entryList.stream()).collect(Collectors.toList());
        }

        return Lists.newArrayList();
    }

}
