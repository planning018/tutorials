import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yxc
 * @date 2022/3/15 6:50 下午
 */
public class TestGson {

    public static void main(String[] args) {
        Gson gson = new Gson();

        List<Double> param = new ArrayList<>();
        param.add(Double.NaN);
        param.add(0.0);

        String result = gson.toJson(param);
        System.out.println(result);
    }
}
