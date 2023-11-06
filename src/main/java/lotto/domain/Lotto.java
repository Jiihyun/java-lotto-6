package lotto.domain;

import lotto.domain.dto.output.LottoDto;
import lotto.exception.ExceptionMessage;

import java.util.List;

import static lotto.exception.ExceptionMessage.LOTTO_NUMBERS_DUPLICATED;


public class Lotto {
    public static final int LOTTO_NUMBERS_LENGTH = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbersLength(numbers);
        validateDuplicatedNumber(numbers);
        this.numbers = getSortedNumbers(numbers);
    }

    private static long getDistinctLength(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .count();
    }

    private List<LottoNumber> getSortedNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .toList();
    }

    private void validateNumbersLength(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_LENGTH) {
            throw ExceptionMessage.LOTTO_NUMBERS_LENGTH.getException();
        }
    }

    private void validateDuplicatedNumber(List<Integer> numbers) {
        if (getDistinctLength(numbers) != LOTTO_NUMBERS_LENGTH) {
            throw LOTTO_NUMBERS_DUPLICATED.getException();
        }
    }

    public LottoDto toLottoDto() {
        List<Integer> lottoNumbers = numbers.stream()
                .map(LottoNumber::getNumber)
                .toList();
        return new LottoDto(lottoNumbers);
    }
}
