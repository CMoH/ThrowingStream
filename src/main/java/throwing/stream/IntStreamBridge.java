package throwing.stream;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import throwing.FunctionBridge;
import throwing.ThrowingIterator.OfInt;
import throwing.ThrowingSpliterator;
import throwing.function.ThrowingBiConsumer;
import throwing.function.ThrowingIntBinaryOperator;
import throwing.function.ThrowingIntConsumer;
import throwing.function.ThrowingIntFunction;
import throwing.function.ThrowingIntPredicate;
import throwing.function.ThrowingIntToDoubleFunction;
import throwing.function.ThrowingIntToLongFunction;
import throwing.function.ThrowingIntUnaryOperator;
import throwing.function.ThrowingObjIntConsumer;
import throwing.function.ThrowingSupplier;

class IntStreamBridge<X extends Throwable> extends BaseStreamBridge<Integer, X, ThrowingIntStream<X>, IntStream>
        implements ThrowingIntStream<X> {
    IntStreamBridge(IntStream delegate, FunctionBridge<X> bridge) {
        super(delegate, bridge);
    }
    
    @Override
    protected ThrowingIntStream<X> getStream() {
        return this;
    }
    
    @Override
    protected ThrowingIntStream<X> updateStream(IntStream delegate) {
        return new IntStreamBridge<>(delegate, getBridge());
    }
    
    @Override
    public OfInt<X> iterator() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ThrowingSpliterator.OfInt<X> spliterator() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ThrowingIntStream<X> filter(ThrowingIntPredicate<? extends X> predicate) {
        return chain(getDelegate().filter(getBridge().convert(predicate)));
    }
    
    @Override
    public ThrowingIntStream<X> map(ThrowingIntUnaryOperator<? extends X> mapper) {
        return chain(getDelegate().map(getBridge().convert(mapper)));
    }
    
    @Override
    public <U> ThrowingStream<U, X> mapToObj(ThrowingIntFunction<? extends U, ? extends X> mapper) {
        return ThrowingStream.of(getDelegate().mapToObj(getBridge().convert(mapper)), getBridge());
    }
    
    @Override
    public ThrowingLongStream<X> mapToLong(ThrowingIntToLongFunction<? extends X> mapper) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ThrowingDoubleStream<X> mapToDouble(ThrowingIntToDoubleFunction<? extends X> mapper) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ThrowingIntStream<X> flatMap(
            ThrowingIntFunction<? extends ThrowingIntStream<? extends X>, ? extends X> mapper) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ThrowingIntStream<X> distinct() {
        return chain(getDelegate().distinct());
    }
    
    @Override
    public ThrowingIntStream<X> sorted() {
        return chain(getDelegate().sorted());
    }
    
    @Override
    public ThrowingIntStream<X> peek(ThrowingIntConsumer<? extends X> action) {
        return chain(getDelegate().peek(getBridge().convert(action)));
    }
    
    @Override
    public ThrowingIntStream<X> limit(long maxSize) {
        return chain(getDelegate().limit(maxSize));
    }
    
    @Override
    public ThrowingIntStream<X> skip(long n) {
        return chain(getDelegate().skip(n));
    }
    
    @Override
    public void forEach(ThrowingIntConsumer<? extends X> action) throws X {
        filterBridgeException(() -> getDelegate().forEach(getBridge().convert(action)));
    }
    
    @Override
    public void forEachOrdered(ThrowingIntConsumer<? extends X> action) throws X {
        filterBridgeException(() -> getDelegate().forEachOrdered(getBridge().convert(action)));
    }
    
    @Override
    public int[] toArray() throws X {
        return filterBridgeException(getDelegate()::toArray);
    }
    
    @Override
    public int reduce(int identity, ThrowingIntBinaryOperator<? extends X> op) throws X {
        return filterBridgeException(() -> getDelegate().reduce(identity, getBridge().convert(op)));
    }
    
    @Override
    public OptionalInt reduce(ThrowingIntBinaryOperator<? extends X> op) throws X {
        return filterBridgeException(() -> getDelegate().reduce(getBridge().convert(op)));
    }
    
    @Override
    public <R> R collect(ThrowingSupplier<R, ? extends X> supplier, ThrowingObjIntConsumer<R, ? extends X> accumulator,
            ThrowingBiConsumer<R, R, ? extends X> combiner) throws X {
        return filterBridgeException(() -> getDelegate().collect(getBridge().convert(supplier),
                getBridge().convert(accumulator), getBridge().convert(combiner)));
    }
    
    @Override
    public int sum() throws X {
        return filterBridgeException(getDelegate()::sum);
    }
    
    @Override
    public OptionalInt min() throws X {
        return filterBridgeException(getDelegate()::min);
    }
    
    @Override
    public OptionalInt max() throws X {
        return filterBridgeException(getDelegate()::max);
    }
    
    @Override
    public long count() throws X {
        return filterBridgeException(getDelegate()::count);
    }
    
    @Override
    public OptionalDouble average() throws X {
        return filterBridgeException(getDelegate()::average);
    }
    
    @Override
    public IntSummaryStatistics summaryStatistics() throws X {
        return filterBridgeException(getDelegate()::summaryStatistics);
    }
    
    @Override
    public boolean anyMatch(ThrowingIntPredicate<? extends X> predicate) throws X {
        return filterBridgeException(() -> getDelegate().anyMatch(getBridge().convert(predicate)));
    }
    
    @Override
    public boolean allMatch(ThrowingIntPredicate<? extends X> predicate) throws X {
        return filterBridgeException(() -> getDelegate().allMatch(getBridge().convert(predicate)));
    }
    
    @Override
    public boolean noneMatch(ThrowingIntPredicate<? extends X> predicate) throws X {
        return filterBridgeException(() -> getDelegate().noneMatch(getBridge().convert(predicate)));
    }
    
    @Override
    public OptionalInt findFirst() throws X {
        return filterBridgeException(getDelegate()::findFirst);
    }
    
    @Override
    public OptionalInt findAny() throws X {
        return filterBridgeException(getDelegate()::findAny);
    }
    
    @Override
    public ThrowingLongStream<X> asLongStream() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ThrowingDoubleStream<X> asDoubleStream() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ThrowingStream<Integer, X> boxed() {
        return ThrowingStream.of(getDelegate().boxed(), getBridge());
    }
}
