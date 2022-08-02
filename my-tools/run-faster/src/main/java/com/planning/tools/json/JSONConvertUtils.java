package com.planning.tools.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * @author yxc
 * @date 2022/8/2 5:02 下午
 */
public class JSONConvertUtils {

    public static void main(String[] args) {
        convertJsonToObject();
    }

    /**
     * Fastjson 转换复杂对象
     */
    public static void convertJsonToObject(){
        String jsonStr = "[{\"rank\":17,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg17.png\",\"name\":\"____________\",\"title\":\"_____?____?_2295\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank17.png\"},{\"rank\":16,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg16.png\",\"name\":\"____________\",\"title\":\"_____?____?_1695\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank16.png\"},{\"rank\":15,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg15.png\",\"name\":\"____________\",\"title\":\"_____?____?_1245\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank15.png\"},{\"rank\":14,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg14.png\",\"name\":\"_?__________\",\"title\":\"_____?____?_945\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank14.png\"},{\"rank\":13,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg13.png\",\"name\":\"____________\",\"title\":\"_____?____?_795\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank13.png\"},{\"rank\":12,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg12.png\",\"name\":\"_?__________\",\"title\":\"_____?____?_645\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank12.png\"},{\"rank\":11,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg11.png\",\"name\":\"____________\",\"title\":\"_____?____?_495\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank11.png\"},{\"rank\":10,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg10.png\",\"name\":\"____________\",\"title\":\"_____?____?_369\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank10.png\"},{\"rank\":9,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg9.png\",\"name\":\"_?__________\",\"title\":\"_____?____?_269\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank9.png\"},{\"rank\":8,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg8.png\",\"name\":\"_?__________\",\"title\":\"_____?____?_189\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank8.png\"},{\"rank\":7,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg7.png\",\"name\":\"____________\",\"title\":\"_____?____?_129\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank7.png\"},{\"rank\":6,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg6.png\",\"name\":\"_____?_?____\",\"title\":\"_____?____?_69\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank6.png\"},{\"rank\":5,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg5.png\",\"name\":\"_?_________?\",\"title\":\"_____?____?_39\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank5.png\"},{\"rank\":4,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg4.png\",\"name\":\"___________?\",\"title\":\"_____?____?_15\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank4.png\"},{\"rank\":3,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg3.png\",\"name\":\"__?________?\",\"title\":\"_____?____?_6\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank3.png\"},{\"rank\":2,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg2.png\",\"name\":\"_?_________?\",\"title\":\"_____?____?_2\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank2.png\"},{\"rank\":1,\"img\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/dg1.png\",\"name\":\"____________\",\"title\":\"_____?____?_0\",\"rankImg\":\"https://bz.wmwi.cn/attachment/yf_zhaocha_resource/images/rank/rank1.png\"}]";
        // 使用 fastjson 转换复杂对象
        List<Map<String, Object>> parseObject = JSON.parseObject(jsonStr, new TypeReference<List<Map<String, Object>>>() {
        });

        System.out.println(parseObject.size());
        System.out.println(parseObject.get(0).keySet());

    }
}
