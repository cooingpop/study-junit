package com.cooingpop.study.junit;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.BooleanSupplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
	@Disabled("Not implemented yet")
	void testSomething() {

	}

	/**
	 * 테스트할 상태가 참인지 확인한다.
	 *
	 * assertTrue(테스트 할 상태, 실패 메세지)
	 * @param value
	 */
	@DisplayName("assertTrue Test01")
	@Test
	@ValueSource(ints = {1, 2, 3, 4})
	void testAssert(Integer value) {
		assertTrue(value > 0, "si greater than 0");
	}

	/**
	 * 두 값이 같은지 확인할 때 사용한다.
	 *
	 * assertEquals(기대 값, 테스트 값, 실패 메세지)
	 */
	@DisplayName("assertEquals")
	@Test
	void testAssertEquals01() {
		int n = 2;
		int m = 2;

		assertEquals(n, m, "equal");
	}

	/**
	 * 두 값이 같지 않은지 확인한다.
	 *
	 * assertNotEquals(기대 값, 테스트 값, 실패 메세지)
	 */
	@DisplayName("assertNotEquals")
	@Test
	void testAssertNotEquals() {
		Integer resultValue = 0;

		assertNotEquals(0, resultValue, "The result cannot be 0");
	}

	/**
	 * 테스트할 상태가 거짓인지 확인한다.
	 * 아래와 같이 FunctionalInterface와 같이 사용할 수 있다.
	 *
	 * assertFalse(테스트 할 상태, 실패 메세지)
	 */
	@DisplayName("assertFalse")
	@Test
	void testAssertFalse() {
		BooleanSupplier condition = () -> 5 < 6;

		assertFalse(condition, "5 is not greater then 6");
	}

	/**
	 * 테스트할 오브젝트가 Null인지 확인
	 *
	 * assertNull(오브젝트, 실패 메세지)
	 */
	@DisplayName("assertNull")
	@Test
	void testAssertNull() {
		Object cat = new Object();

		assertNull(cat, "The cat should be null");
	}

	/**
	 * 두 객체가 같은 주소값을 가지는 오브젝트인지 확인할 때 사용한다. 반대는 assertNotSame이다.
	 * 값을 비교하는 assertEquals와는 다르다.
	 *
	 * assertSame(기대 오브젝트, 대상 오브젝트, 실패 메세지)
	 */
	@DisplayName("assertSame")
	@Test
	void testAssertSame() {
		Integer intA = new Integer("1");
		Integer intB = new Integer("1");

		// assertEquals(intA, intB, "intA and intB should be same"); // Success
		assertSame(intA, intB, "intA and intB should be same"); // Fail
	}

	/**
	 * 강제로 테스트 실패를 시킬때 사용한다.
	 * 개발이 완료되지 않았을 때 사용하면 좋을 것 같다.
	 *
	 * fail(실패 메세지)
	 */
	@DisplayName("fail")
	@Test
	void testFail() {
		fail("FAIL - development is not completed");
	}

	/**
	 * 여러 테스트를 그룹핑 할 때 사용한다.
	 * 이때 그룹핑된 테스트들이 모두 성공해야 최종 성공이다.
	 *
	 * assertAll(그룹핑할 이름, …실행할 테스트(Excutable Function))
	 */
	@DisplayName("assertAll")
	@Test
	void testAssertAll() {
		assertAll("grouped assertions test",
				() -> assertEquals(4, 2 * 2, "4 is 2 times 2"),
				() -> assertEquals("ava", "JAVA".toLowerCase()),
				() -> assertTrue(5 > 4, "5 is greater then 4")
		);
	}

	/**
	 * 예외 테스트를 할때 사용한다.
	 *
	 * assertThrows(기대하는 예외 클래스, 테스트 할 함수)
	 */
	@DisplayName("assertThrows")
	@Test
	void testAssertThrows() {
		Throwable exception = assertThrows(
				IllegalArgumentException.class, () -> {
					throw new ClassNotFoundException("Class Not Found Exception Message");
				}
		);

		assertEquals("IllegalArgument Exception Message", exception.getMessage());
	}

	/**
	 * Timeout 테스트를 할 때 사용한다.
	 * 중요한 점은 테스트할 함수가 Timeout에 걸렸어도 멈추지 않고 계속 실행되는 점이다.
	 *
	 * assertTimeout(Timeout 시간, 테스트 할 함수)
	 */
	@DisplayName("assertTimeout Test01")
	@Test
	void testAssertTimeout() {
		assertTimeout(
				Duration.ofSeconds(1),
				() -> {
					System.out.println("Start Testing...");
					Thread.sleep(3000);
					System.out.println("End Testing...");
				}
		);
	}

	/**
	 * assertTimeout과 목적은 같지만 테스트할 함수가 Timeout에 걸리면 종료된다는 점이 다르다.
	 *
	 * assertTimeoutPreemptively(Timeout 시간, 테스트 할 함수)
	 */
	@DisplayName("assertTimeoutPreemptively")
	@Test
	void testAssertTimeoutPreemptively() {
		assertTimeoutPreemptively(
				Duration.ofSeconds(1),
				() -> {
					System.out.println("Start Testing...");
					Thread.sleep(3000);
					System.out.println("End Testing...");
				}
		);
	}

	@Test
	void testLambda() {
		assertTrue(Arrays.asList(1,2,3)
				.stream()
				.mapToInt(i -> i)
				.sum() > 5, () -> "Sum should be greater than 5");
	}

	/**
	 * 여러 테스트를 그룹핑 할 때 사용한다.
	 * 이때 그룹핑된 테스트들이 모두 성공해야 최종 성공이다.
	 *
	 * 테스트에서 assertion 종류가 많고 복잡한 경우 이용할 수 있다.
	 * assertAll()을 사용하여 assertions 을 그룹화하여 그룹 내에서 실패한 assertions 을 MultipleFailuresError 와 함께 기록할 수 있다.
	 * 즉, 실패의 정확한 위치를 정확히 파악할 수 있기 때문에 보다 복잡한 assertions 을 만들어도 안전하게 사용할 수 있다.
	 *
	 * assertAll(그룹핑할 이름, …실행할 테스트(Excutable Function))
	 */
	@DisplayName("assertAll")
	@Test
	void testGroupAssertions() {
		int[] numbers = {0, 1, 2, 3, 4};
		assertAll("numbers",
				() -> assertEquals(numbers[0], 1),
				() -> assertEquals(numbers[3], 3),
				() -> assertEquals(numbers[4], 4)
		);
	}

	/**
	 * ArrayList, LinkedList와 같이 Iterable 인터페이스를 구현한 구현체들의 값들이 같은지 확인할 때 사용한다.
	 *
	 * assertIterableEquals(기대 값, 테스트 값, 실패 메세지)
	 */
	@DisplayName("assertIterableEquals")
	@Test
	void testAssertIterableEquals() {
		Iterable<String> listOne = new ArrayList<>(Arrays.asList("Java", "JUnit", "Test"));
		Iterable<String> listTwo = new LinkedList<>(Arrays.asList("Java", "Unit", "Test"));

		assertIterableEquals(listOne, listTwo, "listOne should be equal to listTwo");
	}

	@DisplayName("객체만들기 반복 테스트")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@ValueSource(ints = {1, 2, 3, 4})
	public void isBlank (Integer value) {
		log.info("value : {} ", value);
		assertThat(value == 1).isTrue();
	}

	@Test
	void shouldThrowException() {
		Throwable exception = assertThrows(UnsupportedOperationException.class, () -> {
			throw new UnsupportedOperationException("Not supported");
		});
		assertEquals(exception.getMessage(), "Not supported");
	}


}
