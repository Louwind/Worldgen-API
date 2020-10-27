package github.Louwind.Features.util;

import github.Louwind.Features.config.PoolFeatureConfig;
import github.Louwind.Features.context.FeatureContext;
import github.Louwind.Features.function.FeatureFunction;
import github.Louwind.Features.impl.pool.GenericFeaturePool;
import github.Louwind.Features.impl.pool.element.ContextAwarePoolElement;
import github.Louwind.Features.pool.FeaturePool;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;

import java.util.List;

public class JigsawHelper {

    public static FeaturePool getStartPool(PoolFeatureConfig config, List<FeaturePool> pools) {
        StructurePool structurePool = config.getStartPool().get();

        return pools.stream()
                .filter(pool -> pool.getStructurePool() == structurePool)
                .findFirst()
                .orElse(GenericFeaturePool.EMPTY);
    }

    public static void applyFunctions(FeaturePool pool, StructurePoolElement poolElement, FeatureContext context) {
        List<FeatureFunction> functions = pool.getFunctions(poolElement);

        for (FeatureFunction function: functions) {

            if(function.test(context))
                function.accept(context);
        }

    }

    public static void applyContext(StructurePoolElement poolElement, FeatureContext context) {

        if(poolElement instanceof ContextAwarePoolElement) {
            ContextAwarePoolElement element = (ContextAwarePoolElement) poolElement;

            element.setContext(context);
        }

    }

}
