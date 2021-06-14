package github.Louwind.worldgen.util.worldgen;

import com.mojang.serialization.Codec;
import github.Louwind.worldgen.util.codec.CodecHelper;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockRotationChooser {

    public static final Codec<BlockRotationChooser> CODEC = CodecHelper.ROTATION.listOf().xmap(BlockRotationChooser::new, BlockRotationChooser::getRotations);

    public static final BlockRotationChooser ALL = new BlockRotationChooser(Arrays.asList(BlockRotation.values()));

    private final List<BlockRotation> rotations;

    private BlockRotationChooser(List<BlockRotation> rotations) {
        this.rotations = rotations;
    }

    public List<BlockRotation> getRotations() {
        return this.rotations;
    }

    public BlockRotation random(Random random) {
        return this.rotations.isEmpty() ? BlockRotation.random(random) : Util.getRandom(this.rotations, random);
    }

}
