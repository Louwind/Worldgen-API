package github.Louwind.Features.impl.loot;

import github.Louwind.Features.loot.LootBehavior;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class ArmorStandLootBehavior implements LootBehavior<LootableContainerBlockEntity> {

    @Override
    public void setLootTable(Identifier lootTable, ServerWorld server, LootableContainerBlockEntity blockEntity, BlockPos pos, long seed) {
        LootContext context = this.getContext(server, pos, seed);
        LootTable table = this.getLootTable(server, lootTable);

        Box box = new Box(pos);

        for (ArmorStandEntity armorStand : server.getEntitiesByType(EntityType.ARMOR_STAND, box, entity -> true)) {
            List<ItemStack> loot = table.generateLoot(context);

            for (ItemStack stack : loot) {
                EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(stack);

                armorStand.equipStack(equipmentSlot, stack);
            }

        }

    }

}
