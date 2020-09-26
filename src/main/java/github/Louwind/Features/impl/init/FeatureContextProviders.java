package github.Louwind.Features.impl.init;

import github.Louwind.Features.context.FeatureContextProvider;
import github.Louwind.Features.impl.context.GenericContextProvider;
import github.Louwind.Features.impl.context.TreeContextProvider;

public class FeatureContextProviders {

    public static final FeatureContextProvider GENERIC = new GenericContextProvider();
    public static final FeatureContextProvider TREE = new TreeContextProvider();

}
