package name.falgout.jeffrey.throwing.stream;

import java.util.function.Function;

import name.falgout.jeffrey.throwing.ThrowingBaseSpliterator;
import name.falgout.jeffrey.throwing.ThrowingIterator;
import name.falgout.jeffrey.throwing.stream.intermediate.ThrowingBaseStreamIntermediate;
import name.falgout.jeffrey.throwing.stream.terminal.ThrowingBaseStreamTerminal;

public interface ThrowingBaseStream<T, X extends Throwable, S extends ThrowingBaseStream<T, X, S>> extends
    ThrowingBaseStreamIntermediate<S>,
    ThrowingBaseStreamTerminal<T, X> {
  @Override
  public ThrowingIterator<T, X> iterator();

  @Override
  public ThrowingBaseSpliterator<T, X, ?> spliterator();

  /**
   * Returns a stream which will only throw Y and will rethrow any X as Y as specified by the
   * mapper.
   *
   * This is an intermediate operation.
   *
   * @param y
   *          The new exception class
   * @param mapper
   *          A way to convert X exceptions to Ys
   * @return the new stream
   */
  public <Y extends Throwable> ThrowingBaseStream<T, Y, ?> rethrow(Class<Y> y,
      Function<? super X, ? extends Y> mapper);
}
