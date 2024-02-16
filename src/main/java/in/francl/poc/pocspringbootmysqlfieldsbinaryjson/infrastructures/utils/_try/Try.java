package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.infrastructures.utils._try;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Try<T> {

    public abstract boolean isSuccess();

    public abstract boolean isFailure();

    public abstract T get();

    public abstract Throwable getException();

    public abstract <U> Try<U> map(Function<T, U> mapper);

    public abstract <U> Try<U> flatMap(Function<T, Try<U>> mapper);

    public abstract void forEach(Consumer<T> action);

    public static <G> Try<G> of(Supplier<G> supplier) {
        try {
            return new Success<>(supplier.get());
        } catch (Throwable e) {
            return new Failure<>(e);
        }
    }

    public <F> F fold(Function<Try<T>, F> failureFunction, Function<Try<T>, F> successFunction) {
        if (isFailure()) {
            return failureFunction.apply(this);
        } else {
            return successFunction.apply(this);
        }
    }

}
