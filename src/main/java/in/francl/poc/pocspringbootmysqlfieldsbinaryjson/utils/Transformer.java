package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.utils;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class Transformer {


    public static <F, T> T transform(F from, Function<? super F, ? extends T> to) {
        return from == null ? null : to.apply(from);
    }

    public static <F, T> T transform(F from, Supplier<Function<? super F, ? extends T>> to) {
        return transform(from, to.get());
    }


    public static <F, T> Collection<T> transform(Collection<F> from, Function<? super F, ? extends T> to) {
        return from == null ? null : from
            .stream()
            .map(f -> transform(f, to))
            .collect(Collectors.toList())
            ;
    }

    public static <F, T> Collection<T> transform(Collection<F> from, Supplier<Function<? super F, ? extends T>> to) {
        return transform(from, to.get());
    }

}
