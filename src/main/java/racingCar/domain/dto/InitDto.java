package racingCar.domain.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import racingCar.utils.Util;

public class InitDto {
    private final List<String> names;
    private final int count;

    private InitDto(String namesInput, String countInput) {
        names = Arrays.asList(Util.separate(namesInput));
        count = Util.convertToInteger(countInput);
    }

    public static InitDto of(String namesInput, String countInput) {
        return new InitDto(namesInput, countInput);
    }

    public List<String> getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InitDto initDto = (InitDto) o;
        return count == initDto.count && Objects.equals(names, initDto.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names, count);
    }
}