/*
 * SkipTestIfAnnotationPresentExtension.java 2024-01-31
 *
 * @author junyoung.park
 * Copyright 2024. PlayD Corp. All rights Reserved.
 */
package com.cooingpop.study.junit.config;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExtensionContext;

public class SkipTestIfAnnotationPresentExtension implements SkipTestIfAnnotationPresentCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        return SkipTestIfAnnotationPresentCondition.super.evaluateExecutionCondition(context);
    }
}