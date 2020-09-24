package github.Louwind.Features.impl;

import github.Louwind.Features.impl.context.GenericContextProvider;
import github.Louwind.Features.impl.feature.GenericFeature;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class GenericFeatures {

    public static final GenericFeature<GenericContextProvider, DefaultFeatureConfig> WELL = new GenericFeature(new Identifier("features:well"), FeatureContextProviders.GENERIC, DefaultFeatureConfig.CODEC);

}
