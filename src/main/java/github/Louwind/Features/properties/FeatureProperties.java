package github.Louwind.Features.properties;

import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;

import java.util.List;
import java.util.Random;

public interface FeatureProperties {

    List<BlockRotation> getRotations();

    default BlockRotation getRotations(Random random) {
        List<BlockRotation> rotations = this.getRotations();

        if(rotations.isEmpty())
            return BlockRotation.NONE;

        int size = rotations.size();
        int index = random.nextInt(size);

        return rotations.get(index);
    }

    FeaturePropertiesType getType();

}
