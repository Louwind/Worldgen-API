package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.FeatureContextProviderBuilder;
import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.context.provider.GenericContextProvider;
import github.Louwind.Features.impl.context.provider.MetadataContextProvider;
import github.Louwind.Features.impl.context.provider.ThickTreeContextProvider;
import github.Louwind.Features.impl.context.provider.TreeContextProvider;

import static github.Louwind.Features.impl.init.FeatureContextParameters.*;

public class FeatureContextProviders {

    public static final FeatureContextProviderType METADATA = new FeatureContextProviderType(new MetadataContextProvider.Serializer(), () ->  {
        FeatureContextProviderBuilder builder = new FeatureContextProviderBuilder();

        return builder.required(BLOCK_INFO)
                .required(BOX)
                .required(POS)
                .required(RANDOM)
                .required(ROTATION)
                .required(WORLD_ACCESS);
    });

    public static final FeatureContextProviderType PROVIDER = new FeatureContextProviderType(new GenericContextProvider.Serializer(), () ->  {
        FeatureContextProviderBuilder builder = new FeatureContextProviderBuilder();

        return builder.required(BOX)
                .required(CHUNK_POS)
                .required(PIECES)
                .required(POS)
                .required(RANDOM)
                .required(ROOT)
                .required(ROTATION)
                .required(STRUCTURE_POOL)
                .required(STRUCTURE_WORLD_ACCESS);
    });

    public static final FeatureContextProviderType THICK_TREE = new FeatureContextProviderType(new ThickTreeContextProvider.Serializer(), () ->  {
        FeatureContextProviderBuilder builder = new FeatureContextProviderBuilder();

        return builder.allowed(HEIGHT)
                .required(BOX)
                .required(CHUNK_POS)
                .required(ORIGIN)
                .required(PIECES)
                .required(POS)
                .required(RANDOM)
                .required(ROOT)
                .required(ROTATION)
                .required(STRUCTURE_POOL)
                .required(STRUCTURE_WORLD_ACCESS);
    });

    public static final FeatureContextProviderType TREE = new FeatureContextProviderType(new TreeContextProvider.Serializer(), () ->  {
        FeatureContextProviderBuilder builder = new FeatureContextProviderBuilder();

        return builder.allowed(HEIGHT)
                .required(BOX)
                .required(CHUNK_POS)
                .required(PIECES)
                .required(POS)
                .required(RANDOM)
                .required(ROOT)
                .required(ROTATION)
                .required(STRUCTURE_POOL)
                .required(STRUCTURE_WORLD_ACCESS);
    });

}
