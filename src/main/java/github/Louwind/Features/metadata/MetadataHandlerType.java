package github.Louwind.Features.metadata;

import com.mojang.serialization.Codec;

public interface MetadataHandlerType<T extends MetadataHandler> {

    Codec<T> codec();

}
