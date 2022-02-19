package stringcalculator;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final int EMPTY_RETURN_VALUE = 0;
    private static final String CLUE_OF_CUSTOM_DELIMITER = "//";
    private static final String CUSTOM_DELIMITER_AND_EXPRESSION_DELIMITER = "\n";
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String INVALID_OPERAND_EXCEPTION_MESSAGE = "피연산자에 양의 정수 혹은 0이 아닌 값이 있습니다.";
    private static final Pattern POSITIVE_NUMBER_PATTERN = Pattern.compile("^[0-9]");

    public int calculate(final String expression) {
        if (isEmptyOrNull(expression)) {
            return EMPTY_RETURN_VALUE;
        }
        String[] numbers = splitExpression(expression);
        return Arrays.stream(parseToInts(numbers))
                .sum();
    }

    private boolean isEmptyOrNull(String expression) {
        return expression == null || expression.isBlank();
    }

    private String[] splitExpression(String expression) {
        if (expression.startsWith(CLUE_OF_CUSTOM_DELIMITER)) {
            int customDelimiterIndex = 0;
            int expressionIndex = 1;
            String[] customDelimiterAndExpression = expression.split(CUSTOM_DELIMITER_AND_EXPRESSION_DELIMITER);
            String customDelimiter = customDelimiterAndExpression[customDelimiterIndex].substring(2);
            return customDelimiterAndExpression[expressionIndex].split(customDelimiter);
        }
        return expression.split(DEFAULT_DELIMITER);
    }

    private int[] parseToInts(final String[] tokens) {
        validate(tokens);
        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .toArray();
    }


    private void validate(final String[] tokens) {
        if (hasContainsNotPositiveNumber(tokens)) {
            throw new IllegalArgumentException(INVALID_OPERAND_EXCEPTION_MESSAGE);
        }
    }

    private boolean hasContainsNotPositiveNumber(final String[] tokens) {
        return Arrays.stream(tokens)
                .anyMatch(token -> !POSITIVE_NUMBER_PATTERN.matcher(token).matches());
    }
}