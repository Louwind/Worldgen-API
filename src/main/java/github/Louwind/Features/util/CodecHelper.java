package github.Louwind.Features.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.function.Supplier;

public class CodecHelper {

    public static <E extends Enum> Codec<E> createEnum(Supplier<E[]> enums) {
        return Codec.INT.comapFlatMap(ordinal -> {
            E value = enums.get()[ordinal];

            return DataResult.success(value);
        }, Enum::ordinal);
    }

}
