package github.Louwind.worldgen.core.event.structure.pool;

import com.mojang.datafixers.util.Pair;
import github.Louwind.worldgen.mixin.AccessorStructurePool;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@FunctionalInterface
public interface StructurePoolCallback {

    Event<StructurePoolCallback> EVENT = StructurePoolCallback.Holder.createEvent();

    void append(AccessorStructurePool accessor, List<StructurePoolElement> elements, List<Pair<StructurePoolElement, Integer>> elementCounts);

    static Event<StructurePoolCallback> event(StructurePool pool) {
        return event(pool.getId());
    }

    static Event<StructurePoolCallback> event(Identifier poolId) {
        return Holder.eventLocal(poolId);
    }

    class Holder {
        private static final Map<Identifier, Event<StructurePoolCallback>> eventMap = new ConcurrentHashMap<>();

        public static Event<StructurePoolCallback> eventLocal(Identifier poolId) {
            return eventMap.computeIfAbsent(poolId, id -> createEvent());
        }

        private static Event<StructurePoolCallback> createEvent() {
            return EventFactory.createArrayBacked(StructurePoolCallback.class,
                    listeners -> (accessor, elements, elementCounts) -> {
                        for (StructurePoolCallback callback : listeners)
                            callback.append(accessor, elements, elementCounts);
                    }
            );
        }

    }

}
