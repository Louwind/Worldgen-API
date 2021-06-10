package github.Louwind.Features.loot;

import com.mojang.serialization.Codec;
import net.minecraft.util.JsonSerializableType;
import net.minecraft.util.JsonSerializer;

public interface LootBehaviorType<T extends LootBehavior<?>> {

    Codec<T> codec();

}
