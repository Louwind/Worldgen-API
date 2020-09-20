package github.Louwind.Features.impl.feature;

import github.Louwind.Features.impl.context.DefaultFeatureContext;
import github.Louwind.Features.impl.entry.DefaultFeatureEntry;
import github.Louwind.Features.impl.pool.DefaultFeaturePool;
import github.Louwind.Features.impl.properties.DefaultFeatureProperties;

import java.util.Optional;

@Deprecated
public class DefaultFeatureGenerator {

    protected final DefaultFeatureProperties settings;

    public DefaultFeatureGenerator(DefaultFeatureProperties settings) {
        this.settings = settings;
    }

//    @Deprecated
//    protected <T> List<T> fromPools(Function<DefaultFeaturePool, List<T>> poolfunction, Function<FeaturePoolEntry, List<T>> entryFunction, FeatureContext context) {
//        StructurePool structurePool = null;
//        DefaultFeaturePool[] generatorPools = this.getPools();
//
//        List<DefaultFeaturePool> pools = Stream.of(generatorPools)
//                .filter(pool -> pool.getStructurePool().get(context) == structurePool)
//                .collect(Collectors.toList());
//
//        List<T> functions = pools.stream()
//                .map(poolfunction::apply)
//                .flatMap(List::stream)
//                .collect(Collectors.toList());
//
//        for (DefaultFeaturePool pool : pools) {
//            Optional<FeaturePoolEntry> entryOptional = this.getEntry(pool, context);
//
//            if (entryOptional.isPresent()) {
//                FeaturePoolEntry entry = entryOptional.get();
//                List<T> entryFunctions = entryFunction.apply(entry);
//
//                return Stream.concat(functions.stream(), entryFunctions.stream()).collect(Collectors.toList());
//            }
//
//        }
//
//        return functions;
//    }

    protected Optional<DefaultFeatureEntry> getEntry(DefaultFeaturePool pool, DefaultFeatureContext context) {
//        RotatedStructurePiece piece = context.get(PIECE);
//        IdentifiedPoolElement poolElement = (IdentifiedPoolElement) piece.getPoolElement();

//        return pool.getEntries().stream().filter(entry -> poolElement.getId().equals(entry.getId())).findFirst();
        return Optional.ofNullable(null);
    }

//    public List<FeatureFunction> getFunctions(FeatureContext context) {
//        return this.fromPools(DefaultFeaturePool::getFunctions, FeaturePoolEntry::getFunctions, context);
//    }

    public DefaultFeaturePool[] getPools() {
//
//        if (this.parent != null) {
//            Optional<StructureGenerator> optional = STRUCTURE_GENERATOR.getOrEmpty(this.parent);
//
//            if (optional.isPresent()) {
//                StructureGenerator parent = optional.get();
//
//                return Stream.of(parent.getPools(), this.pools).flatMap(Stream::of).toArray(FeaturePool[]::new);
//            }
//        }
//
//        return this.pools;
        return null;
    }

//    public List<FeatureContextSetter> getSetters(FeatureContextBuilder builder) {
//        return this.fromPools(DefaultFeaturePool::getSetters, FeaturePoolEntry::getSetters, builder.build());
//    }

}
