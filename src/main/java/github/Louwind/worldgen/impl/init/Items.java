package github.Louwind.worldgen.impl.init;

import github.Louwind.worldgen.impl.item.DebugStickItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class Items {

    public static final Item BEDROOM_DEBUG_STICK = new DebugStickItem(new Identifier("worldgen:bedroom"), new Item.Settings());

    public static final Item BOOKSHELF_DEBUG_STICK = new DebugStickItem(new Identifier("worldgen:bookshelf"), new Item.Settings());

    public static final Item DRESS_ROOM_DEBUG_STICK = new DebugStickItem(new Identifier("worldgen:dress_room"), new Item.Settings());

    public static final Item KITCHEN_DEBUG_STICK = new DebugStickItem(new Identifier("worldgen:kitchen"), new Item.Settings());

    public static final Item WELL_DEBUG_STICK = new DebugStickItem(new Identifier("worldgen:well"), new Item.Settings());

    public static final Item WINE_STORAGE_DEBUG_STICK = new DebugStickItem(new Identifier("worldgen:wine_storage"), new Item.Settings());

}
