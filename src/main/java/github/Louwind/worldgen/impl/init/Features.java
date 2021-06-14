package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.worldgen.feature.JigsawFeature;
import github.Louwind.worldgen.impl.worldgen.feature.JigsawFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class Features {

    public static final Feature<JigsawFeatureConfig> JIGSAW = new JigsawFeature(JigsawFeatureConfig.CODEC);

}