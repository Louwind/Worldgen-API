package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.worldgen.feature.JigsawFeature;
import github.Louwind.Features.impl.worldgen.feature.JigsawFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class FeatureRegistry {

    public static final Feature<JigsawFeatureConfig> JIGSAW = new JigsawFeature(JigsawFeatureConfig.CODEC);

}
