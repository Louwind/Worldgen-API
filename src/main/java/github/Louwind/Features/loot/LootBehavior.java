package github.Louwind.Features.loot;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import static net.minecraft.loot.context.LootContextParameters.ORIGIN;

public interface LootBehavior<T extends BlockEntity> {

    @SuppressWarnings("unchecked")
    default void generate(ServerWorld server, BlockEntity blockEntity, BlockPos pos) {
        var seed = this.getSeed(server);
        var context = this.getContext(server, pos, seed);

        this.setLootTable(context, server, (T) blockEntity, pos, seed);
    }

    default LootContext getContext(ServerWorld world, BlockPos pos, long seed) {
        return new LootContext.Builder(world)
                .parameter(ORIGIN, Vec3d.ofCenter(pos))
                .random(seed)
                .build(LootContextTypes.CHEST);
    }

    default ItemStack getRandomStack(LootContext context, ServerWorld server) {
        var loot = this.getLootTable(server).generateLoot(context);
        var random = context.getRandom();

        if(!loot.isEmpty()) {
            var size = loot.size();
            var index = random.nextInt(size);

            return loot.get(index);
        }

        return ItemStack.EMPTY;
    }

    default long getSeed(ServerWorld server) {
        return server.random.nextLong();
    }

    LootBehaviorType<?> getType();

    LootTable getLootTable(ServerWorld world);

    void setLootTable(LootContext context, ServerWorld server, T blockEntity, BlockPos pos, long seed);

}