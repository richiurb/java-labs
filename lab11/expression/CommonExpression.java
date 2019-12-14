package expression;

public interface CommonExpression extends Expression, TripleExpression {
    default boolean isOrdered() {
        return false;
    }

    int getPriority();
}