package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.context.GenericContextProvider;
import github.Louwind.Features.impl.feature.GenericFeature;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class GenericFeatures {

    public static final GenericFeature<GenericContextProvider, DefaultFeatureConfig> THIN_SPRUCE = new GenericFeature(new Identifier("features:thin_spruce"), FeatureContextProviders.TREE, DefaultFeatureConfig.CODEC);
    public static final GenericFeature<GenericContextProvider, DefaultFeatureConfig> WELL = new GenericFeature(new Identifier("features:well"), FeatureContextProviders.GENERIC, DefaultFeatureConfig.CODEC);

}
