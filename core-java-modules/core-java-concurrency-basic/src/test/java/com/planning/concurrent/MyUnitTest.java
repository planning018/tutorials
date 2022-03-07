package com.planning.concurrent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: planning
 * @Date: 2019/5/26 11:38
 */
public class MyUnitTest {

    private static Logger logger = LoggerFactory.getLogger(MyUnitTest.class);

    // character replace
    private String replace(String truckLength, String occupyTruckLength) {
        // 场景1： 3.4,4.7,8 ---> 3.4/4.7/8米
        //String str1 = "3.4,4.7,8";

        String truckLengthResult = dealTruckLength(truckLength);

        String occupyTruckLengthResult = "";
        if (!StringUtils.isBlank(occupyTruckLength)) {
            occupyTruckLengthResult = "/占_米".replace("_", occupyTruckLength);
        }

        if (StringUtils.isBlank(truckLengthResult)) {
            occupyTruckLengthResult = occupyTruckLengthResult.replace("/", "");
        }

        return truckLengthResult + occupyTruckLengthResult;
    }

    private String dealTruckLength(String truckLength) {
        if (StringUtils.isBlank(truckLength)) {
            logger.info("字符串不合法");
            return "";
        }

        if ("-1".equals(truckLength)) {
            return "不限车长";
        }

        return truckLength.replace(",", "/") + "米";

/*        List<String> str1List = Arrays.asList(truckLength.split(","));
        if(str1List.contains("-1")){
            return "不限车长";
        }
        StringBuilder sbf = new StringBuilder();
        for(String str : str1List){
            sbf.append(str);
            if((str1List.indexOf(str) + 1) != str1List.size()){
                sbf.append("/");
            }
        }
        sbf.append("米");
        return sbf.toString();*/
    }

    private String finalTruckLengthNew(String truckLength, String occupyTruckLength) {
        List<String> lengthList = Lists.newArrayList();
        if (StringUtils.isNotBlank(truckLength)) {
            List<String> realLengthList = Arrays.asList(truckLength.split(","));
            if (realLengthList.contains("-1")) {
                lengthList.add("不限车长");
            } else {
                lengthList.addAll(realLengthList);
                int index = lengthList.size() - 1;
                lengthList.set(index, lengthList.get(index) + "米");
            }
        }

        if (StringUtils.isNotBlank(occupyTruckLength)) {
            lengthList.add("占_米".replace("_", occupyTruckLength));
        }

        return StringUtils.join(lengthList.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList()), "/");
    }

    @Test
    public void testSubstring() {
/*        String str = "hello";
        System.out.println(str.substring(0, 3));

        System.out.println("result is :" + 12432311 % 100);
        System.out.println("result is :" + 12432312 / 100);*/

        //String complexString = "1a0123a0sdsa01sdas";
        //System.out.println(complexString.replaceAll("a0","a0 \n"));


        // System.out.println(StringUtils.strip("[a,b]","[]"));
        System.out.println(String.valueOf(System.currentTimeMillis()));
        System.out.println(String.valueOf(System.currentTimeMillis() - 60));

    }

    @Test
    public void testListCompare() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(2, 3);

        //System.out.println(list1.containsAll(list2));

        List<Long> list3 = Lists.newArrayList(1L);
        list3.remove(1L);
        System.out.println(list3.subList(0,list3.size()));
    }

    @Test
    public void test() {
        String truckLength = "1.2,2.3,4";
        String truckLength1 = "-1";
        String truckLength2 = "";
        String truckLength3 = "1.2";

        String occupyTruckLength = "1.7";
        String occupyTruckLength1 = " ";
        String occupyTruckLength2 = null;
        //System.out.println("result is " + replace(truckLength, occupyTruckLength));
        //logger.info("result is {}", replace(truckLength2,occupyTruckLength));
        System.out.println(dealTruckLength(truckLength3));

        //logger.info(finalTruckLengthNew(truckLength,occupyTruckLength2));
    }

    public class Person {

        private String name;
        private String address;

        public Person(String name, String address) {
            this.name = name;
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "name is " + this.name + ", address is " + this.address;
        }
    }

    @Test
    public void testObject() {
        Person p1 = new Person("zhangsan", "123");
        p1 = changePerson(p1, true);
        System.out.println(p1.toString());
    }

    private Person changePerson(Person p1, boolean judge) {
        if (judge) {
            Person p2 = new Person("lisi", "234");
            return p2;
        }
        return p1;
    }

    /**
     * @author planning
     * @since 2020-04-28 10:34
     **/
    @Data
    public class TestListResponse {

        private List<Person> personList;
    }

    @Test
    public void testStream() {
        // 准备数据
        Person p1 = new Person("zhangsan", "234");
        Person p2 = new Person("lisi", "234");

        List<Person> personList = Lists.newArrayList(p1, p2);
        TestListResponse response = new TestListResponse();
        response.setPersonList(personList);

        // 进行测试，使用 orElseGet 优化输出
        System.out.println(JSON.toJSONString(Optional.ofNullable(response).map(TestListResponse::getPersonList).orElseGet(Lists::newArrayList)));
    }

    @Test
    public void testMapValue() {
        Map map = new HashMap();
        System.out.println(map.values());

        System.out.println(map.remove("1"));

        //System.out.println(hailstone(27));
        //System.out.println(hailstone(42));
    }

    private int hailstone(int n) {
        int length = 1;
        while (n > 1) {
            n = n % 2 == 0 ? n / 2 : 3 * n + 1;
            System.out.print(n+ " ");
            length++;
        }
        return length;
    }

    @Data
    public class PhoneFilterResultVO {

        private String reason;

        private Long taskId;

        private Long taskTypeId;

        private Boolean testValue;

        private List<Map<String,Object>> extData;

    }

    @Test
    public void testJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("reason", "无效的手机号");
        map.put("taskId", 125485);
        map.put("taskTypeId", 90005352);
        map.put("bizId", "111");
        map.put("source", "cargo");

        Map<String, Object> extData = new HashMap<>();
        extData.put("startCity", "杭州");
        extData.put("platformFrom", "ABC");
        extData.put("cargoId", 1313131L);

        map.put("extData", extData);

        String str = JSONObject.toJSONString(map);
        System.out.println(str);

        // 此处注意要用 List<Map<String,Object>> map 接收
        PhoneFilterResultVO phoneFilterResultVO = JSON.parseObject(str, PhoneFilterResultVO.class);

        System.out.println(Objects.nonNull(phoneFilterResultVO.getTestValue()) ? !phoneFilterResultVO.getTestValue() : null);

        System.out.println(JSON.toJSONString(phoneFilterResultVO));
    }

    @Test
    public void testCal(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),23,59,59);
        System.out.println(calendar.getTime());
    }

    @Test
    public void testCall(){
        Person p = new Person("","");
        doCallPerson(p);
        System.out.println(JSON.toJSONString(p));
    }

    private void doCallPerson(Person personList){
        personList.setAddress("123");
    }

    @Test
    public void testList(){
        List<Long> testList = Collections.emptyList();
        System.out.println(testList.contains(1L));
    }

    @Test
    public void testDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");
        System.out.println(formatter.format(LocalDate.now()));
        System.out.println(formatter.format(new Date(1607416537000L).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
    }

    @Test
    public void testReplace() {
        String text = "<font color='#FA871E' style='font-size:14px;'><fontsize size='14px'>请于date前补齐保证金，否则抢单需读秒</fontsize></font>";
        System.out.println(text.replace("date", "2020/10/18"));
    }

}