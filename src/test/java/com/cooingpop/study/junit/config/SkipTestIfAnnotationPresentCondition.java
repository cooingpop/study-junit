/*
 * SkipTestIfAnnotationPresentCondition.java 2024-01-31
 *
 * @author junyoung.park
 * Copyright 2024. PlayD Corp. All rights Reserved.
 */
package com.cooingpop.study.junit.config;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * junit 테스트할 때 특정 테스트 메서드 skip 할때 사용 가능
 * 사용 방법 : @Test 어노테이션 있는 메서드위에 @SkipTestIfAnnotationPresent 어노테이션 추가
 */
public interface SkipTestIfAnnotationPresentCondition extends ExecutionCondition {

    @Override
    default ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        AnnotatedElement element = context.getElement().orElse(null);

        if (element != null && isAnnotationPresent(element, SkipTestIfAnnotationPresent.class)) {
            return ConditionEvaluationResult.disabled("Test is disabled due to the presence of @config.SkipTestIfAnnotationPresent");
        }

        return ConditionEvaluationResult.enabled("Test is enabled");
    }

    default boolean isAnnotationPresent(AnnotatedElement element, Class<? extends Annotation> annotationClass) {
        return element.getAnnotation(annotationClass) != null;
    }
}