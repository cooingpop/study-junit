/*
 * JunitApplicationTests03.java 2024-01-31
 *
 * @author junyoung.park
 * Copyright 2024. PlayD Corp. All rights Reserved.
 */
package com.cooingpop.study.junit;

import com.cooingpop.study.junit.config.SkipTestIfAnnotationPresent;
import com.cooingpop.study.junit.config.SkipTestIfAnnotationPresentExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SkipTestIfAnnotationPresentExtension.class)
@ActiveProfiles("dev")
@SpringBootTest
@Slf4j
public class JunitApplicationTests03 {

    @BeforeEach
    public void setResources() {
        log.info("리소스 생성 완료");
    }

    @Order(1)
    @DisplayName("테스트1")
    @Test
    public void test1() {
        assertDoesNotThrow(() -> System.out.println("테스트1"));
    }

    @Order(2)
    @DisplayName("테스트2")
    @Test
    public void test2() {
        assertDoesNotThrow(() -> System.out.println("테스트2"));
    }

    // 특정 테스트만 스킵할 수 있는 어노테이션
    @SkipTestIfAnnotationPresent
    @Order(3)
    @DisplayName("테스트3")
    @Test
    public void test3() {
        assertDoesNotThrow(() -> System.out.println("테스트3"));
    }

    @Order(4)
    @DisplayName("테스트4")
    @Test
    public void test4() {
        assertDoesNotThrow(() -> System.out.println("테스트4"));
    }
}
