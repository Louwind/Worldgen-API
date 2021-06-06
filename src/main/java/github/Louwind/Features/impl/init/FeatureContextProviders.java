package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.context.provider.*;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureContextProviders {

    public static final FeatureContextProviderType METADATA = new FeatureContextProviderType(new MetadataContextProvider.Serializer(), builder -> builder.required(BLOCK_INFO, MIRROR, POS, RANDOM, ROTATION, WORLD));

    public static final FeatureContextProviderType EMPTY = new FeatureContextProviderType(new EmptyContextProvider.Serializer(), builder -> {});

    public static final FeatureContextProviderType PIECE = new FeatureContextProviderType(new PieceContextProvider.Serializer(), builder -> builder.optional(FeatureContextParameters.PIECE, WORLD).required(BOX, CHUNK_GENERATOR, PIECES, POS, RANDOM, ROTATION));

    public static final FeatureContextProviderType REPEAT = new FeatureContextProviderType(new RepeatContextProvider.Serializer(), builder -> builder.required(INDEX));

}
