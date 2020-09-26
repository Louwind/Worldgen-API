package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.provider.FeatureContextProviderType;
import github.Louwind.Features.impl.context.provider.GenericContextProvider;
import github.Louwind.Features.impl.context.provider.TreeContextProvider;

public class FeatureContextProviders {

    public static final FeatureContextProviderType GENERIC = new FeatureContextProviderType(new GenericContextProvider.Serializer());
    public static final FeatureContextProviderType TREE = new FeatureContextProviderType(new TreeContextProvider.Serializer());

}
