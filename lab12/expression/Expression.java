package expression;

import expression.ToMiniString;

public interface Expression extends ToMiniString {
    int evaluate(int x);
}