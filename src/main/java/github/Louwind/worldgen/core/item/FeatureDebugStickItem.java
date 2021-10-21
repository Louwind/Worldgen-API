package github.Louwind.worldgen.core.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.util.registry.BuiltinRegistries.CONFIGURED_FEATURE;

/**
 * An item that generates a configured feature when you right-click a block
 * */
public class FeatureDebugStickItem extends Item {

    public FeatureDebugStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        var nbtCompound = stack.getNbt();

        if (nbtCompound != null && nbtCompound.contains("id")) {
            var id = nbtCompound.getString("id");

            if(id != null && !id.isEmpty()) {
                var formattings = new Formatting[]{Formatting.ITALIC, Formatting.GRAY};
                var mutableText = new TranslatableText(id);

                mutableText.formatted(formattings);
                tooltip.add(mutableText);
            }

        }

    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var nbtCompound = context.getStack().getNbt();
        var pos = context.getBlockPos();
        var world = context.getWorld();

        var random = world.getRandom();
        var origin = pos.up(2);

        if(!world.isClient && world instanceof ServerWorld server && nbtCompound != null) {
            var chunkGenerator = server.getChunkManager().getChunkGenerator();
            var id = Identifier.tryParse(nbtCompound.getString("id"));

            if(id != null && CONFIGURED_FEATURE.containsId(id)) {
                var feature = CONFIGURED_FEATURE.get(id);

                if(feature != null && feature.generate(server, chunkGenerator, random, origin))
                    return ActionResult.CONSUME;
            }

        }

        return super.useOnBlock(context);
    }

}
