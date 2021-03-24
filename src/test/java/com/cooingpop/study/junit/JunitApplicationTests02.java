/*
 * @(#) JunitApplicationTests02.java 2021. 03. 24.
 *
 * Copyright 2021. PlayD Corp. All rights Reserved.
 */
package com.cooingpop.study.junit;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author 박준영
 */

@Log4j2
public class JunitApplicationTests02 {

	@Test
	void test() {
		log.info("TEST");
	}

	@DisplayName("testAssert")
	@ParameterizedTest
	@ValueSource(strings = {"2021-05-01", "2021-05-02", "2021-05-03"})
	void testAssert(String ymd) {
		log.info("parameter : {}" ,ymd);
		final String yesterYmd = LocalDate.parse(ymd).minusDays(1).format(DateTimeFormatter.ISO_DATE);
		final String startYmd = LocalDate.parse(ymd).getDayOfMonth() == 1 ? LocalDate.parse(ymd).minusMonths(1).format(DateTimeFormatter.ISO_DATE) :
				LocalDate.parse(ymd).getDayOfWeek().getValue() == 1 ? LocalDate.of(LocalDate.parse(ymd).getYear(), LocalDate.parse(ymd).getMonth(), 1).toString() : yesterYmd;

		assertEquals(startYmd, "2021-05-01");
	}

	@Test
	void testLambda() {
		log.info("testLambda");
		assertTrue(Arrays.asList(1,2,3)
				.stream()
				.mapToInt(i -> i)
				.sum() > 9, () -> "Sum should be greater than 5");
	}

}
