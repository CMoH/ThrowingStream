package throwing.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import throwing.Nothing;
import throwing.bridge.ThrowingBridge;

public class ThrowingStreamTest {
    public static final String MESSAGE = "Exceptional flow control";
    public Stream<Integer> numbers = IntStream.range(0, 20).boxed();
    
    @Test
    public void worksCorrectlyWithExceptions() {
        ThrowingStream<Integer, Exception> s = ThrowingBridge.of(numbers, Exception.class);
        
        List<Integer> collected = new ArrayList<>();
        Exception e = new Exception(MESSAGE);
        try {
            s.filter(i -> {
                if (i >= 10) {
                    throw e;
                } else {
                    return true;
                }
            }).forEach(collected::add);
            fail();
        } catch (Exception e2) {
            assertSame(e, e2);
            assertEquals(MESSAGE, e.getMessage());
        }
        
        assertEquals(10, collected.size());
    }
    
    @Test
    public void worksCorrectlyWithoutExceptions() {
        ThrowingStream<Integer, Nothing> s = ThrowingBridge.of(numbers);
        long l = s.filter(i -> i < 10).count();
        assertEquals(10, l);
    }
}
