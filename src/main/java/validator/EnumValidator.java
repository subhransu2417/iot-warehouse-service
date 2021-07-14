package validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class validates the Enum Constraints
 */
public class EnumValidator implements ConstraintValidator<EnumConstraint, Object> {

    private static final Logger LOG = LoggerFactory.getLogger(EnumValidator.class);

    private List<String> acceptedValues;

    @Override
    public void initialize(EnumConstraint annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;
        try {
            if (value == null) {
                return true;
            }
            return acceptedValues.contains(value.toString());
        } catch (Exception e) {
            LOG.error("Error Validating value {} against Enum {}", value, acceptedValues, e);
        }
        return result;
    }
}
