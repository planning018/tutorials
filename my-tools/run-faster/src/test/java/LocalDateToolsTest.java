import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author yxc
 * @date 2022/7/12 2:04 下午
 */
public class LocalDateToolsTest {

    @Test
    public void testTimeFormat(){
        LocalDate specialDate = LocalDate.parse("2022-07-12");
        System.out.println(specialDate.minusDays(2));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parse = LocalDate.parse("20220712", formatter);
        System.out.println(parse);
    }
}
