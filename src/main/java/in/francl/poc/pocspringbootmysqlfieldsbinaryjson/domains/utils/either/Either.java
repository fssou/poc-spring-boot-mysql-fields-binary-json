package in.francl.poc.pocspringbootmysqlfieldsbinaryjson.domains.utils.either;

import java.util.Objects;
import java.util.function.Function;

public class Either<L, R> {
    private final L left;
    private final R right;

    protected Either() {
        this.left = null;
        this.right = null;
    }
    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L left) {
        Objects.requireNonNull(left);
        return new Either<>(left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        Objects.requireNonNull(right);
        return new Either<>(null, right);
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public L getLeft() {
        if (isLeft()) {
            return left;
        } else {
            throw new IllegalStateException("Either.getRight() called on a Right");
        }
    }

    public R getRight() {
        if (isRight()) {
            return right;
        } else {
            throw new IllegalStateException("Either.getLeft() called on a Left");
        }
    }

    public <T> T fold(Function<L, T> leftFunction, Function<R, T> rightFunction) {
        if (isLeft()) {
            return leftFunction.apply(left);
        } else {
            return rightFunction.apply(right);
        }
    }

}
