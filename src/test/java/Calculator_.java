import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Calculator_ {

    private static String separators = "\\D";

    @Test
    public void given_empty_string_should_return_0 () throws Exception {
        assertThat(Calculator.add("")).isEqualTo(0);
    }

    @Test
    public void given_a_number_should_return_it() throws Exception {
        assertThat(Calculator.add("4")).isEqualTo(4);
    }

    @Test
    public void given_3_4_numbers_should_return_seven() throws Exception {
        assertThat(Calculator.add("3,4")).isEqualTo(7);
    }

    @Test
    public void given_a_set_of_numbers_separated_by_commas_numbers_should_return_the_sum_of_them() throws Exception {
        assertThat(Calculator.add("2,3,4")).isEqualTo(9);
    }

    @Test
    public void given_a_set_of_numbers_separated_by_commas_or_newlines_numbers_should_return_the_sum_of_them() throws Exception {
        assertThat(Calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void given_a_bad_usage_of_separators_set_of_numbers_should_return_the_sum_of_numbers() throws Exception {
        assertThat(Calculator.add("1,\n")).isEqualTo(1);
    }

    @Test
    public void given_a_negative_number_return_exception() throws Exception {
        assertThat(Calculator.add("-1")).isEqualTo(0);
    }

    @Test
    public void given_a_delimiter_return_the_sum_of_numbers() throws Exception {
        assertThat(Calculator.add("//;\n1;2")).isEqualTo(3);
    }

    @Test
    public void given_a_set_of_numbers_bigger_that_1000_return_the_sum_of_the_others() throws Exception {
        assertThat(Calculator.add("2,1001")).isEqualTo(2);
    }

    @Test
    public void given_delimiters_of_any_length_return_the_sum_of_numbers() throws Exception {
        assertThat(Calculator.add("//[***]\n1***2***3")).isEqualTo(6);
    }

    @Test
    public void given_multiple_delimiters_return_the_sum_of_numbers() throws Exception {
        assertThat(Calculator.add("//[*][%]\n1*2%3")).isEqualTo(6);
    }

    private static class Calculator {
        public static int add(String numbers) throws Exception {
            return Arrays.stream(tokenize(numbers))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .filter(n -> n > 0 & n<1000)
                    .sum();
        }

        private static String[] tokenize(String numbers) throws Exception {
            return numbers.split(separators);
        }
    }
}
