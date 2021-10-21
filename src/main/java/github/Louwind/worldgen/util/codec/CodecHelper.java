package github.Louwind.worldgen.util.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.util.BlockRotation;

import java.util.function.Function;

public class CodecHelper {

    public static final Codec<BlockRotation> ROTATION = createEnum(BlockRotation::valueOf);

    public static <E extends Enum<?>> Codec<E> createEnum(Function<String, E> valueOf) {
        return Codec.STRING.comapFlatMap(str -> {
            E value = valueOf.apply(str.toUpperCase());

            return DataResult.success(value);
        }, enumValue -> enumValue.name().toLowerCase()).stable();
    }

}
