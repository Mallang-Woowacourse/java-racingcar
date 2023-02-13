package racingcar.domain.game;

public class RandomNumberGenerator implements NumberGenerator {

    private static final int ONE = 1;

    @Override
    public int generate(final int minNumber, final int maxNumber) {
        return (int) ((Math.random() * (maxNumber - minNumber + ONE)) + minNumber);
    }
}