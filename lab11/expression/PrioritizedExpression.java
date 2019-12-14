package expression;

public interface PrioritizedExpression extends Expression, TripleExpression {
    int getPriority();
}