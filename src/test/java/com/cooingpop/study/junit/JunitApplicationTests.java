package com.cooingpop.study.junit;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Log4j2
class JunitApplicationTests {

	/**
	 * Test class : 최소한 하나의 테스트 메서드를 포함하는 최상위 클래스, abstract 이면 안되고 single constructor 이어야 한다.
	 */

	/**
	 * Test Method
	 *
	 * @Test, @RepeatedTest, @parameterizedTest, @TestFactory, @TestTemplate로 선언된 테스트 메서드
	 */

	/**
	 * Lifecycle Method
	 *
	 * @BeforeAll, @AfterAll, @BeforeEach, @AfterEach로 선언된 메서드
	 *
	 * Unit 테스트케이스 작성 시 개발자가 테스트 수행 전 후 특정 시점에 실행될 동작을 정의할 수 있다.
	 *
	 * 기존에는 @BeforeClass, @AfterClass, @Before, @After 어노테이션을 활용했는데.
	 *
	 * JUnit5에서는 @BeforeAll, @AfterAll, @BeforeEach, @AfterEach 로 변경되었다.
	 */

	@Test
	void contextLoads() {
	}

	/**
	 * 해당 annotation 이 달린 메서드가 현재 클레스의 모든 테스트 메서드보다 먼저 실행
	 * 해당 메서드는 static이어야 함.
	 * @BeforeClass와 동일
	 */
	@BeforeAll
	static void setup() {
		log.info("@BeforeAll");
	}

	/**
	 * 해당 annotation이 달린 메서드가 각 테스트 메서드 전에 실행된다.
	 * @Before와 동일
	 */
	@BeforeEach
	void init() {
		log.info("@BeforeEach");
	}

	/**
	 * 테스트 클래스 또는 테스트 메서드의 이름을 정의
	 */
	@DisplayName("Single test")
	@Test
	void testSingle() {
		log.info("success");
	}

	/**
	 * 테스트 클래스 또는 메서드를 비활성화
	 * 기존 @Ignore와 어노테이션 대체
	 */
	@Test
	@Disabled("Not implemented yest")
	void testSomething() {

	}

	@Test
	void lambdaExpressions() {
		assertTrue(Arrays.asList(1,2,3)
				.stream()
				.mapToInt(i -> i)
				.sum() > 5, () -> "Sum should be greater than 5");
	}

	/**
	 * 테스트에서 assertion 종류가 많고 복잡한 경우 이용할 수 있다.
	 * assertAll()을 사용하여 assertions 을 그룹화하여 그룹 내에서 실패한 assertions 을 MultipleFailuresError 와 함께 기록할 수 있다.
	 * 즉, 실패의 정확한 위치를 정확히 파악할 수 있기 때문에 보다 복잡한 assertions 을 만들어도 안전하게 사용할 수 있다.
	 */
	@Test
	void groupAssertions() {
		int[] numbers = {0, 1, 2, 3, 4};
		assertAll("numbers",
				() -> assertEquals(numbers[0], 1),
				() -> assertEquals(numbers[3], 3),
				() -> assertEquals(numbers[4], 1)
		);
	}

	@Test
	void shouldThrowException() {
		Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
			throw new UnsupportedOperationException("Not supported");
		});
		assertEquals(exception.getMessage(), "Not supported");
	}

	@Test
	void timeSleep() {
		assertTimeout(Duration.ofSeconds(2), () -> {
			log.info("Before Sleep");
			Thread.sleep(3000);
			log.info("After Sleep");
		});
		//  위처럼 할 경우 테스트가 실패해도 실행하게 된다. 이럴 경우에는 아래 방법을 사용
		//	assertTimeoutPreemptively();
	}
}
