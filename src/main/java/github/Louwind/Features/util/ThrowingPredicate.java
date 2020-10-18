package github.Louwind.Features.util;

import java.util.function.Consumer;
import java.util.function.Predicate;

@FunctionalInterface
public interface ThrowingPredicate<T, E extends Throwable> extends Predicate<T>{

    @SuppressWarnings("unchecked")
    static <T, E extends Throwable> Predicate<T> orFalse(Consumer<E> consumer, ThrowingPredicate<T, E> predicate) {
        return t -> {
            try {
                return predicate.throwable(t);
            } catch (Throwable e) {
                consumer.accept((E) e);

                return false;
            }
        };
    }

    static <T, E extends Throwable> Predicate<T> rethrow(ThrowingPredicate<T, E> predicate) throws E {
        return t -> {
            try {
                return predicate.throwable(t);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Override
    default boolean test(T elem) {
        try {
            return throwable(elem);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    boolean throwable(T elem) throws E;

}
