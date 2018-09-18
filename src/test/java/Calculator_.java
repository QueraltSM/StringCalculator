import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Calculator_ {

    private static final String separators = "[,;\n]";

    @Test
    public void given_empty_string_should_return_0 () {
        assertThat(Calculator.add("")).isEqualTo(0);
    }

    @Test
    public void given_a_number_should_return_it(){
        assertThat(Calculator.add("4")).isEqualTo(4);
    }

    @Test
    public void given_3_4_numbers_should_return_seven(){
        assertThat(Calculator.add("3,4")).isEqualTo(7);
    }

    @Test
    public void given_a_set_of_numbers_separated_by_commas_numbers_should_return_the_sum_of_them(){
        assertThat(Calculator.add("2,3,4")).isEqualTo(9);
    }

    @Test
    public void given_a_set_of_numbers_separated_by_commas_or_newlines_numbers_should_return_the_sum_of_them(){
        assertThat(Calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void given_a_bad_usage_of_separators_set_of_numbers_should_return_the_sum_of_numbers(){
        assertThat(Calculator.add("1,\n")).isEqualTo(1);
    }

    @Test
    public void given_a_negative_number_return_exception(){
        assertThat(Calculator.add("-1")).isEqualTo(0);
    }

    @Test
    public void given_a_delimiter_return_the_sum_of_numbers(){
        assertThat(Calculator.add("//;\n1;2")).isEqualTo(3);
    }


    private static class Calculator {

        public static int add(String numbers) {
            return Arrays.stream(tokenize(numbers))
                    .filter(s -> !s.isEmpty())
                    .filter(s -> s.matches("[0-9]+"))
                    .mapToInt(Integer::parseInt)
                    .filter(n -> n > 0)
                    .sum();
        }

        private static String[] tokenize(String numbers) {
            return numbers.split(separators);
        }
    }
}
