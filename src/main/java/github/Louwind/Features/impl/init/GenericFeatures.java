package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.feature.GenericFeature;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class GenericFeatures {

    public static final GenericFeature<DefaultFeatureConfig> THICK_STRIPPED_SPRUCE = new GenericFeature(new Identifier("features:thick_stripped_spruce"), DefaultFeatureConfig.CODEC);

    public static final GenericFeature<DefaultFeatureConfig> THIN_SPRUCE = new GenericFeature(new Identifier("features:thin_spruce"), DefaultFeatureConfig.CODEC);

    public static final GenericFeature<DefaultFeatureConfig> WELL = new GenericFeature(new Identifier("features:well"), DefaultFeatureConfig.CODEC);

}
