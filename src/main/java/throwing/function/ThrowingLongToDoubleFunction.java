package throwing.function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongToDoubleFunction;

import javax.annotation.Nullable;

import throwing.Nothing;

@FunctionalInterface
public interface ThrowingLongToDoubleFunction<X extends Throwable> {
    public double applyAsDouble(long value) throws X;

    default public LongToDoubleFunction fallbackTo(LongToDoubleFunction fallback) {
        ThrowingLongToDoubleFunction<Nothing> t = fallback::applyAsDouble;
        return orTry(t)::applyAsDouble;
    }

    default public <Y extends Throwable> ThrowingLongToDoubleFunction<Y> orTry(
            ThrowingLongToDoubleFunction<? extends Y> f) {
        return orTry(f, null);
    }

    default public <Y extends Throwable> ThrowingLongToDoubleFunction<Y> orTry(
            ThrowingLongToDoubleFunction<? extends Y> f,
            @Nullable Consumer<? super Throwable> thrown) {
        return t -> {
            ThrowingSupplier<Double, X> s = () -> applyAsDouble(t);
            return s.orTry(() -> f.applyAsDouble(t), thrown).get();
        };
    }

    default public <Y extends Throwable> ThrowingLongToDoubleFunction<Y> rethrow(Class<X> x,
            Function<? super X, ? extends Y> mapper) {
        return t -> {
            ThrowingSupplier<Double, X> s = () -> applyAsDouble(t);
            return s.rethrow(x, mapper).get();
        };
    }
}
