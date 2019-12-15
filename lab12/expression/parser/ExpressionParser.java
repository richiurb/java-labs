package expression.parser;

import expression.*;
import java.util.Map;

public class ExpressionParser extends BaseParser implements Parser {
    private String lastOperator = ")";
    private static final int topLevel = 3;
    private static final int singleTokenLevel = 0;

    private static final Map<String, Integer> priorities = Map.of(
        "+", 2,
        "-", 2,
        "*", 1,
        "/", 1,
        "<<", 3,
        ">>", 3,
        ")", topLevel + 1
    );
    
    private static final Map<Character, String> firstCharToOperator = Map.of(
        '+', "+",
        '-', "-",
        '*', "*",
        '/', "/",
        ')', ")",
        '<', "<<",
        '>', ">>"
    );

    @Override
    public TripleExpression parse(String expression) {
        setSource(new ExpressionScanner(expression + ')'));
        nextChar();
        
        return parseLevel(topLevel);
    }

    private CommonExpression parseLevel(int level) {
        if (level == singleTokenLevel) {
            return parseNextToken();
        }

        CommonExpression expression = parseLevel(level - 1);

        while (priorities.get(lastOperator) == level) {
            expression = makeExpression(lastOperator, expression, parseLevel(level - 1));
        }

        if (level == topLevel) {
            tryGetOperator();
        }

        return expression;
    }

    private CommonExpression parseNextToken() {
        if (test('-')) {
            nextChar();

            if (between('0', '9')) {
                return getConst(true);
            }

            return Negative.getNegativeExpression(parseLevel(0));
        }
        
        if (test('(')) {
            nextChar();
            return parseLevel(topLevel);
        }
        
        if (between('0', '9')) {
            return getConst(false);
        }
        
        return getVariable();
    }

    private CommonExpression getVariable() {
        StringBuilder stringBuilder = new StringBuilder();

        while (!tryGetOperator()) {
            stringBuilder.append(ch);
            nextChar();
        }
        
        return new Variable(stringBuilder.toString());
    }

    private CommonExpression getConst(boolean isNegative) {
        StringBuilder stringBuilder = new StringBuilder(isNegative ? "-" : "");

        do {
            stringBuilder.append(ch);
            nextChar();
        } while (between('0', '9'));

        tryGetOperator();

        try {
            return new Const(Integer.parseInt(stringBuilder.toString()));
        } catch (NumberFormatException e) {
            throw error("Illegal variable: " + stringBuilder.toString());
        }
    }

    private boolean tryGetOperator() {
        if (!firstCharToOperator.containsKey(ch)) {
            return false;
        }

        String operator = firstCharToOperator.get(ch);
        expect(operator);
        lastOperator = operator;

        return true;
    }

    private CommonExpression makeExpression(String operator, CommonExpression a, CommonExpression b) {
        switch (operator) {
            case "+":
                return new Add(a, b);
            case "-":
                return new Subtract(a, b);
            case "*":
                return new Multiply(a, b);
            case "/":
                return new Divide(a, b);
            case "<<":
                return new LeftShift(a, b);
            case ">>":
                return new RightShift(a, b);     
            default:
                throw error("Unsupported operator: " + operator);
        }
    }
}
