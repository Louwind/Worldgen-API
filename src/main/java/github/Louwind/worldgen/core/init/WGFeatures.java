package github.Louwind.worldgen.core.init;

import github.Louwind.worldgen.core.world.gen.feature.JigsawFeature;
import github.Louwind.worldgen.core.world.gen.feature.JigsawFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class WGFeatures {

    public static final Feature<JigsawFeatureConfig> JIGSAW = new JigsawFeature(JigsawFeatureConfig.CODEC);

}
