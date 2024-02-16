package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try;

import java.util.function.Consumer;
import java.util.function.Function;

public class Success<T> extends Try<T> {
    private final T value;

    protected Success(T value) {
        this.value = value;
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean isFailure() {
        return false;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public Throwable getException() {
        throw new UnsupportedOperationException("Success doesn't have an exception.");
    }

    @Override
    public <U> Try<U> map(Function<T, U> mapper) {
        try {
            return new Success<>(mapper.apply(value));
        } catch (Throwable e) {
            return new Failure<>(e);
        }
    }

    @Override
    public <U> Try<U> flatMap(Function<T, Try<U>> mapper) {
        try {
            return mapper.apply(value);
        } catch (Throwable e) {
            return new Failure<>(e);
        }
    }

    @Override
    public void forEach(Consumer<T> action) {
        action.accept(value);
    }
}

