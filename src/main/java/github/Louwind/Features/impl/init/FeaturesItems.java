package github.Louwind.Features.impl.init;

import github.Louwind.Features.item.DebugStickItem;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class FeaturesItems {

    public static final Item THIN_SPRUCE_DEBUG_STICK = new DebugStickItem(GenericFeatures.THIN_SPRUCE, DefaultFeatureConfig::new, new Item.Settings());
    public static final Item WELL_DEBUG_STICK = new DebugStickItem(GenericFeatures.WELL, DefaultFeatureConfig::new, new Item.Settings());

}
