package github.Louwind.Features.impl.init;

import com.google.common.base.Suppliers;
import github.Louwind.Features.impl.item.DebugStickItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import static net.minecraft.world.gen.feature.DefaultFeatureConfig.INSTANCE;

public class FeaturesItems {

    public static final Item BOOKSHELF_DEBUG_STICK = new DebugStickItem(new Identifier("features:bookshelf"), Suppliers.ofInstance(INSTANCE), new Item.Settings());

    public static final Item KITCHEN_DEBUG_STICK = new DebugStickItem(new Identifier("features:kitchen"), Suppliers.ofInstance(INSTANCE), new Item.Settings());

    public static final Item THICK_STRIPPED_SPRUCE_DEBUG_STICK = new DebugStickItem(new Identifier("features:thick_stripped_spruce"), Suppliers.ofInstance(INSTANCE), new Item.Settings());

    public static final Item THIN_SPRUCE_DEBUG_STICK = new DebugStickItem(new Identifier("features:thin_spruce"), Suppliers.ofInstance(INSTANCE), new Item.Settings());

    public static final Item WELL_DEBUG_STICK = new DebugStickItem(new Identifier("features:well"), Suppliers.ofInstance(INSTANCE), new Item.Settings());

    public static final Item WINE_STORAGE_DEBUG_STICK = new DebugStickItem(new Identifier("features:wine_storage"), Suppliers.ofInstance(INSTANCE), new Item.Settings());

}
