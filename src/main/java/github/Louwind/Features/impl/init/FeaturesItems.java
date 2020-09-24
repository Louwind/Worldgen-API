package github.Louwind.Features.impl.init;

import github.Louwind.Features.impl.GenericFeatures;
import github.Louwind.Features.item.DebugStickItem;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class FeaturesItems {

    public static final Item WELL_DEBUG_STICK = new DebugStickItem(GenericFeatures.WELL, DefaultFeatureConfig::new, new Item.Settings());

}
