package github.Louwind.worldgen.util;

import com.mojang.serialization.Codec;
import github.Louwind.worldgen.loot.LootBehavior;
import github.Louwind.worldgen.loot.LootBehaviorType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class LootBehaviorList {

    public static final Codec<LootBehaviorList> CODEC = LootBehaviorType.CODEC.listOf().xmap(LootBehaviorList::new, LootBehaviorList::getLootBehaviors);

    private final List<LootBehavior<?>> lootBehaviors;

    public LootBehaviorList(List<LootBehavior<?>> lootBehaviors) {
        this.lootBehaviors = lootBehaviors;
    }

    public void generate(ServerWorld server, BlockEntity blockEntity, BlockPos pos) {
        this.lootBehaviors.forEach(lootBehavior -> lootBehavior.generate(server, blockEntity, pos));
    }

    public List<LootBehavior<?>> getLootBehaviors() {
        return this.lootBehaviors;
    }

}
