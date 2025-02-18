package com.alibaba.fastjson2.schema;

import com.alibaba.fastjson2.JSONObject;

import java.math.BigInteger;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

final class IntegerSchema extends JSONSchema {
    final long minimum;
    final boolean exclusiveMinimum;

    final long maximum;
    final boolean exclusiveMaximum;

    final long multipleOf;

    IntegerSchema(JSONObject input) {
        super(input);
        Object exclusiveMinimum = input.get("exclusiveMinimum");

        long minimum = input.getLongValue("minimum", Long.MIN_VALUE);
        if (exclusiveMinimum == Boolean.TRUE) {
            this.exclusiveMinimum = true;
            this.minimum = minimum;
        } else if (exclusiveMinimum instanceof Number) {
            this.exclusiveMinimum = true;
            this.minimum = input.getLongValue("exclusiveMinimum");
        } else {
            this.minimum = minimum;
            this.exclusiveMinimum = false;
        }

        long maximum = input.getLongValue("maximum", Long.MIN_VALUE);
        Object exclusiveMaximum = input.get("exclusiveMaximum");
        if (exclusiveMaximum == Boolean.TRUE) {
            this.exclusiveMaximum = true;
            this.maximum = maximum;
        } else if (exclusiveMaximum instanceof Number) {
            this.exclusiveMaximum = true;
            this.maximum = input.getLongValue("exclusiveMaximum");
        } else {
            this.exclusiveMaximum = false;
            this.maximum = maximum;
        }

        this.multipleOf = input.getLongValue("multipleOf", 0);
    }

    @Override
    public Type getType() {
        return Type.Integer;
    }

    @Override
    public ValidateResult validate(Object value) {
        if (value == null) {
            return FAIL_INPUT_NULL;
        }

        Class valueClass = value.getClass();
        if (valueClass == Byte.class
                || valueClass == Short.class
                || valueClass == Integer.class
                || valueClass == Long.class
                || valueClass == BigInteger.class
                || valueClass == AtomicInteger.class
                || valueClass == AtomicLong.class
        ) {
            if (minimum != Long.MIN_VALUE) {
                long longValue = ((Number) value).longValue();
                if (exclusiveMinimum ? longValue <= minimum : longValue < minimum) {
                    return new ValidateResult.MinimumFail(minimum, value, exclusiveMinimum);
                }
            }

            if (maximum != Long.MIN_VALUE) {
                long longValue = ((Number) value).longValue();
                if (exclusiveMaximum ? longValue >= maximum : longValue > maximum) {
                    return new ValidateResult.MaximumFail(maximum, value, exclusiveMaximum);
                }
            }

            if (multipleOf != 0) {
                long longValue = ((Number) value).longValue();
                if (longValue % multipleOf != 0) {
                    return new ValidateResult.MultipleOfFail(multipleOf, (Number) value);
                }
            }
            return SUCCESS;
        }

        return new ValidateResult.TypeNotMatchFail(Type.Integer, valueClass);
    }

    @Override
    public ValidateResult validate(long longValue) {
        if (minimum != Long.MIN_VALUE) {
            if (exclusiveMinimum ? longValue <= minimum : longValue < minimum) {
                return new ValidateResult.MinimumFail(minimum, longValue, exclusiveMinimum);
            }
        }

        if (maximum != Long.MIN_VALUE) {
            if (exclusiveMaximum ? longValue >= maximum : longValue > maximum) {
                return new ValidateResult.MaximumFail(maximum, longValue, exclusiveMaximum);
            }
        }

        if (multipleOf != 0) {
            if (longValue % multipleOf != 0) {
                return new ValidateResult.MultipleOfFail(multipleOf, longValue);
            }
        }
        return SUCCESS;
    }

    @Override
    public ValidateResult validate(Long value) {
        if (value == null) {
            return FAIL_INPUT_NULL;
        }

        long longValue = value.longValue();
        if (minimum != Long.MIN_VALUE) {
            if (exclusiveMinimum ? longValue <= minimum : longValue < minimum) {
                return new ValidateResult.MinimumFail(minimum, value, exclusiveMinimum);
            }
        }

        if (maximum != Long.MIN_VALUE) {
            if (exclusiveMaximum ? longValue >= maximum : longValue > maximum) {
                return new ValidateResult.MaximumFail(maximum, value, exclusiveMaximum);
            }
        }

        if (multipleOf != 0) {
            if (longValue % multipleOf != 0) {
                return new ValidateResult.MultipleOfFail(multipleOf, longValue);
            }
        }
        return SUCCESS;
    }

    @Override
    public ValidateResult validate(Integer value) {
        if (value == null) {
            return FAIL_INPUT_NULL;
        }

        long longValue = value.longValue();
        if (minimum != Long.MIN_VALUE) {
            if (exclusiveMinimum ? longValue <= minimum : longValue < minimum) {
                return new ValidateResult.MinimumFail(minimum, value, exclusiveMinimum);
            }
        }

        if (maximum != Long.MIN_VALUE) {
            if (exclusiveMaximum ? longValue >= maximum : longValue > maximum) {
                return new ValidateResult.MaximumFail(maximum, value, exclusiveMaximum);
            }
        }

        if (multipleOf != 0) {
            if (longValue % multipleOf != 0) {
                return new ValidateResult.MultipleOfFail(multipleOf, longValue);
            }
        }
        return SUCCESS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.alibaba.fastjson2.schema.IntegerSchema that = (com.alibaba.fastjson2.schema.IntegerSchema) o;
        return Objects.equals(title, that.title)
                && Objects.equals(description, that.description)
                && Objects.equals(minimum, that.minimum)
                && Objects.equals(exclusiveMinimum, that.exclusiveMinimum)
                && Objects.equals(maximum, that.maximum)
                && Objects.equals(exclusiveMaximum, that.exclusiveMaximum)
                && Objects.equals(multipleOf, that.multipleOf)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, minimum, exclusiveMinimum, maximum, exclusiveMaximum, multipleOf);
    }
}
