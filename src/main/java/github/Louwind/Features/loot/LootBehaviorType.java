package github.Louwind.Features.loot;

import com.mojang.serialization.Codec;
import github.Louwind.Features.util.LootBehaviorList;

import static github.Louwind.Features.registry.Registries.LOOT_BEHAVIOR;

public interface LootBehaviorType<T extends LootBehavior<?>> {

    Codec<LootBehavior<?>> CODEC = LOOT_BEHAVIOR.dispatch("type", LootBehavior::getType, LootBehaviorType::codec);

    Codec<LootBehaviorList> LIST_CODEC = CODEC.listOf().xmap(LootBehaviorList::new, LootBehaviorList::getLootBehaviors);

    Codec<T> codec();

}
