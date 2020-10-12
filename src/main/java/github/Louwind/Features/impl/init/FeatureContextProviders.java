package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.context.provider.GenericContextProvider;
import github.Louwind.Features.impl.context.provider.MetadataContextProvider;
import github.Louwind.Features.impl.context.provider.ThickTreeContextProvider;
import github.Louwind.Features.impl.context.provider.TreeContextProvider;

public class FeatureContextProviders {

    public static final FeatureContextProviderType METADATA = new FeatureContextProviderType(new MetadataContextProvider.Serializer());

    public static final FeatureContextProviderType PROVIDER = new FeatureContextProviderType(new GenericContextProvider.Serializer());

    public static final FeatureContextProviderType THICK_TREE = new FeatureContextProviderType(new ThickTreeContextProvider.Serializer());

    public static final FeatureContextProviderType TREE = new FeatureContextProviderType(new TreeContextProvider.Serializer());

}
