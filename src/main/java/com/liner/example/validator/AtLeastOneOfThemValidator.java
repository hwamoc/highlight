package com.liner.example.validator;

import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.stream.Stream;

public class AtLeastOneOfThemValidator implements ConstraintValidator<AtLeastOneOfThem, Object> {
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private String[] fields;

    public void initialize(AtLeastOneOfThem constraint) {
        fields = constraint.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        long notNull = Stream.of(fields)
                .map(field -> PARSER.parseExpression(field).getValue(value))
                .filter(Objects::nonNull)
                .count();
        return notNull > 0;
    }

}
