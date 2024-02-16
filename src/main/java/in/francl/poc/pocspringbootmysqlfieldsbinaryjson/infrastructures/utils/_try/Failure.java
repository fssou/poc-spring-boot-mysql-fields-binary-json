package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try;

import java.util.function.Consumer;
import java.util.function.Function;

public class Failure<T> extends Try<T> {
    private final Throwable exception;

    protected Failure(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isFailure() {
        return true;
    }

    @Override
    public T get() {
        throw new UnsupportedOperationException("Failure doesn't have a value.");
    }

    @Override
    public Throwable getException() {
        return exception;
    }

    @Override
    public <U> Try<U> map(Function<T, U> mapper) {
        // Failure remains a Failure, so we return the same Failure instance
        return new Failure<>(exception);
    }

    @Override
    public <U> Try<U> flatMap(Function<T, Try<U>> mapper) {
        // Failure remains a Failure, so we return the same Failure instance
        return new Failure<>(exception);
    }

    @Override
    public void forEach(Consumer<T> action) {
        // No action is performed for a Failure
    }
}
