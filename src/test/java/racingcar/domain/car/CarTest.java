package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.mock.MockFixedNumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Car 는 ")
class CarTest {

    private static final String MALLANG_NAME = "말랑";
    private final Car car = new Car(MALLANG_NAME);

    @Test
    @DisplayName("Name을 받아 생성되고 position은 0으로 초기화 된다.")
    void test_1() {
        // when
        Car car = new Car(MALLANG_NAME);

        // then
        assertThat(car.name().value()).isEqualTo(MALLANG_NAME);
        assertThat(car.position()).isEqualTo(Position.init());
    }

    @ParameterizedTest(name = "move() 시 생성되는 값이 3 이하(ex: {arguments})이면 멈춘다.")
    @ValueSource(ints = {0, 1, 2, 3})
    void test_2(final int randomNumber) {
        // given
        Position beforeMovePosition = car.position();

        // when
        car.move(new MockFixedNumberGenerator(randomNumber));

        // then
        assertThat(car.position()).isEqualTo(beforeMovePosition);
    }

    @ParameterizedTest(name = "move() 시 생성되는 값이 4 이상(ex: {arguments})이면 움직인다.")
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void test_3(final int randomNumber) {
        // given
        Position beforeMovePosition = car.position();

        // when
        car.move(new MockFixedNumberGenerator(randomNumber));

        // then
        assertThat(car.position()).isEqualTo(beforeMovePosition.increase());
    }

    @ParameterizedTest(name = "move() 시 생성되는 값이 0 ~ 9 사이가 아니라면(ex: {arguments}) 오류가 발생한다.")
    @ValueSource(ints = {-1, 10})
    void test_4(final int randomNumber) {
        // when & then
        assertThatThrownBy(() -> car.move(new MockFixedNumberGenerator(randomNumber)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}