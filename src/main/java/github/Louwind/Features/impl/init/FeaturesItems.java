package github.Louwind.Features.impl.init;

import com.google.common.base.Suppliers;
import github.Louwind.Features.impl.item.DebugStickItem;
import net.minecraft.item.Item;

import static github.Louwind.Features.impl.init.GenericFeatures.*;
import static net.minecraft.world.gen.feature.DefaultFeatureConfig.INSTANCE;

public class FeaturesItems {

    public static final Item THICK_STRIPPED_SPRUCE_DEBUG_STICK = new DebugStickItem(THICK_STRIPPED_SPRUCE, Suppliers.ofInstance(INSTANCE), new Item.Settings());

    public static final Item THIN_SPRUCE_DEBUG_STICK = new DebugStickItem(THIN_SPRUCE, Suppliers.ofInstance(INSTANCE), new Item.Settings());

    public static final Item WELL_DEBUG_STICK = new DebugStickItem(WELL, Suppliers.ofInstance(INSTANCE), new Item.Settings());

}
