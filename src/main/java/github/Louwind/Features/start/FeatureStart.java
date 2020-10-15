package github.Louwind.Features.start;

import github.Louwind.Features.entry.FeatureEntry;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.mixin.AccessorSinglePoolElement;
import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents all {@link FeaturePool} that a structure built with jigsaws can have
 * */
public interface FeatureStart {

    default List<FeatureFunction> getFunctions(FeaturePool pool, StructurePoolElement poolElement) {
        return this.fromPools(pool, poolElement, FeaturePool::getFunctions, FeatureEntry::getFunctions);
    }

    FeatureStartType getType();

    List<FeaturePool> getPools();

    default FeaturePool getRandomPool(Random random) {
        List<FeaturePool> pools = this.getPools();
        int index = random.nextInt(pools.size());

        return pools.get(index);
    }

    @Deprecated
    default <T> List<T> fromPools(FeaturePool pool, StructurePoolElement poolElement, Function<FeaturePool, List<T>> poolfunction, Function<FeatureEntry, List<T>> entryFunction) {

        List<T> list = poolfunction.apply(pool);
        Optional<FeatureEntry> entryOptional = this.getEntry(pool, poolElement);

        if (entryOptional.isPresent()) {
            FeatureEntry entry = entryOptional.get();
            List<T> entryList = entryFunction.apply(entry);

            return Stream.concat(list.stream(), entryList.stream()).collect(Collectors.toList());
        }

        return list;
    }

    @Deprecated
    default Optional<FeatureEntry> getEntry(FeaturePool pool, StructurePoolElement poolElement) {

        if(poolElement instanceof SinglePoolElement) {
            SinglePoolElement singlePoolElement = (SinglePoolElement) poolElement;
            AccessorSinglePoolElement accessor = (AccessorSinglePoolElement) singlePoolElement;

            Optional<Identifier> optional = accessor.getStructure().left();

            if(optional.isPresent()) {
                Identifier id = optional.get();

                return pool.getEntries().stream().filter(entry -> entry.getStructureId().equals(id)).findFirst();
            }

        }

        return Optional.empty();
    }

}
