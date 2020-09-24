package github.Louwind.Features.generator;

import github.Louwind.Features.context.setter.FeatureContextSetter;
import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.mixin.SinglePoolElementAccessor;
import github.Louwind.Features.pool.FeaturePool;
import github.Louwind.Features.properties.FeatureProperties;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface FeatureGenerator {

    default List<FeatureFunction> getFunctions(FeaturePool pool, StructurePoolElement poolElement) {
        return this.fromPools(pool, poolElement, FeaturePool::getFunctions, FeatureEntry::getFunctions);
    }

    default List<FeatureContextSetter> getSetters(FeaturePool pool, StructurePoolElement poolElement) {
        return this.fromPools(pool, poolElement, FeaturePool::getSetters, FeatureEntry::getSetters);
    }

    FeatureGeneratorType getType();

    List<FeaturePool> getPools();

    FeatureProperties getProperties();

    default FeaturePool getRandomPool(Random random) {
        List<FeaturePool> pools = this.getPools();
        int index = random.nextInt(pools.size());

        return pools.get(index);
    }

    default <T> List<T> fromPools(FeaturePool pool, StructurePoolElement poolElement, Function<FeaturePool, List<T>> poolfunction, Function<FeatureEntry, List<T>> entryFunction) {

        List<FeaturePool> pools = this.getPools().stream()
                .filter(featurePool -> featurePool == pool)
                .collect(Collectors.toList());

        List<T> list = pools.stream()
                .map(poolfunction::apply)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        for (FeaturePool featurePool : pools) {
            Optional<FeatureEntry> entryOptional = this.getEntry(featurePool, poolElement);

            if (entryOptional.isPresent()) {
                FeatureEntry entry = entryOptional.get();
                List<T> entryList = entryFunction.apply(entry);

                return Stream.concat(list.stream(), entryList.stream()).collect(Collectors.toList());
            }

        }

        return list;
    }

    default Optional<FeatureEntry> getEntry(FeaturePool pool, StructurePoolElement poolElement) {

        if(poolElement instanceof SinglePoolElement) {
            SinglePoolElement singlePoolElement = (SinglePoolElement) poolElement;
            SinglePoolElementAccessor accessor = (SinglePoolElementAccessor) singlePoolElement;

            Optional<Identifier> optional = accessor.getStructure().left();

            if(optional.isPresent()) {
                Identifier id = optional.get();

                return pool.getEntries().stream().filter(entry -> entry.getStructureId().equals(id)).findFirst();
            }

        }

        return Optional.empty();
    }

}
