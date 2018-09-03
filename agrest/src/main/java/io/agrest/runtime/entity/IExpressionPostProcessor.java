package io.agrest.runtime.entity;

import io.agrest.meta.LrEntity;
import org.apache.cayenne.exp.Expression;

/**
 * @since 2.2
 */
public interface IExpressionPostProcessor {

    /**
     * Perform any post-processing and cleanup that might be necessary for the successful execution of expression
     * @return Expression that is ready for execution
     * @throws io.agrest.LinkRestException if expression is malformed or violates validation constraints
     *
     * @since 2.2
     */
    Expression process(LrEntity<?> entity, Expression exp);
}