package github.Louwind.Features.util.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;

import java.util.stream.DoubleStream;

public class PrimitiveCodecHelper {

    public static final Codec<DoubleStream> DOUBLE_STREAM = new PrimitiveCodec<>() {

        @Override
        public <T> DataResult<DoubleStream> read(final DynamicOps<T> ops, final T input) {
            return ops.getStream(input).flatMap(stream -> {

                if (stream.allMatch(element -> ops.getNumberValue(element).result().isPresent()))
                    return DataResult.success(stream.mapToDouble(element -> ops.getNumberValue(element).result().orElseThrow().doubleValue()));

                return DataResult.error("Some elements are not ints: " + input);
            });
        }

        @Override
        public <T> T write(DynamicOps<T> ops, DoubleStream value) {
            return ops.createList(value.mapToObj(ops::createDouble));
        }

        @Override
        public String toString() {
            return "DoubleStream";
        }
    };

}