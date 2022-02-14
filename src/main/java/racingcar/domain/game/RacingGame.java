package racingcar.domain.game;

import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.domain.exception.GetWinnerBeforeFinishException;
import racingcar.domain.result.MidtermResult;
import racingcar.domain.result.WinnerResult;

public class RacingGame {

    private final RandomNumberGenerator randomNumberGenerator;
    private TryCount tryCount;
    private Cars cars;

    public RacingGame(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void proceedTurn() {
        tryCount.increment();
        cars.move();
    }

    public boolean isFinished() {
        return tryCount.isReachedMaxCount();
    }

    public WinnerResult getWinnerResult() {
        if (!isFinished()) {
            throw new GetWinnerBeforeFinishException();
        }
        return cars.getWinnerResult();
    }

    public MidtermResult getMidtermResult() {
        return cars.getMidtermResult();
    }

    public void enrollCars(List<String> names) {
        cars = new Cars(names, randomNumberGenerator);
    }

    public void initTryCount(int inputTryCount) {
        tryCount = new TryCount(inputTryCount);
    }
}