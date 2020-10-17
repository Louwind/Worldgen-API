package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.context.provider.GenericContextProvider;
import github.Louwind.Features.impl.context.provider.ThickTreeContextProvider;
import github.Louwind.Features.impl.context.provider.TreeContextProvider;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureContextProviders {

    public static final FeatureContextProviderType PROVIDER = new FeatureContextProviderType(new GenericContextProvider.Serializer(), builder -> builder.optional(PIECE).required(BOX, CHUNK_GENERATOR, CHUNK_POS, PIECES, POS, RANDOM, ROOT, ROTATION, STRUCTURE_POOL, WORLD));

    public static final FeatureContextProviderType METADATA = new FeatureContextProviderType(null, builder -> builder.required(BLOCK_INFO, MIRROR, POS, RANDOM, ROTATION, WORLD));

    public static final FeatureContextProviderType THICK_TREE = new FeatureContextProviderType(new ThickTreeContextProvider.Serializer(), builder -> builder.required(BOX, CHUNK_GENERATOR, CHUNK_POS, ORIGIN, PIECES, POS, RANDOM, ROOT, ROTATION, STRUCTURE_POOL, WORLD));

    public static final FeatureContextProviderType TREE = new FeatureContextProviderType(new TreeContextProvider.Serializer(), builder -> builder.optional(HEIGHT).required(BOX, CHUNK_GENERATOR, CHUNK_POS, PIECES, POS, RANDOM, ROOT, ROTATION, STRUCTURE_POOL, WORLD));

}
