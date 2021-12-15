package com.planning.pigeon.bean;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.planning.pigeon.test.ConvertUtils.serializeObject;

/**
 * @author yxc
 * @date 2021/12/15 4:25 下午
 */
public class GeneratePigeonCallParam {

    static ObjectMapper mapper = new ObjectMapper();

    public String serializeObject(Object obj) throws Exception {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    @Test
    public void testGenerateGtrModelParam() throws Exception {
        // 根据接口提供的参数类型，生存相应的对象
        List<String> listStr = new ArrayList<>();
        listStr.add("{\"c_weight\":20.0,\"d_avg_order_cargo_kilos\":0.0,\"is_c_second_category_id_same_d_pre_cargo_second_category_id\":0,\"c_expect_freight\":330000,\"s_ccr_7d\":0.0,\"is_same_truck_length\":0,\"c_cargo_type\":3,\"d_top1_gps_rate_30d\":0.0,\"c_start_addr_lon\":118.786425,\"d_avg_call_cargo_kilos\":0.0,\"d_avg_call_cargo_weight\":0.0,\"d_top1_gps_distance\":0,\"d_view_cnt_30d\":0,\"d_top123_start_city_cargo_cnt\":0,\"ds_call_cnt_30d\":0,\"is_same_truck_type\":0,\"d_truck_type\":0,\"is_c_end_city_same_d_pre_fixed_city_id\":0,\"s_ctr_7d\":0.0,\"d_top123_end_city_cargo_cnt\":0,\"r_supply_demand_ratio_30d\":0.0,\"d_click_cnt_30d\":0,\"d_call_cnt_30d\":0,\"d_top1_6line_cargo_cnt\":0,\"r_truck_length_c_ratio_7d\":0.0,\"is_pre_same_truck_type\":0,\"c_end_addr_lat\":39.93512,\"d_top1_end_city_cargo_cnt_30d\":0.0,\"d_click_cargo_cnt_30d\":0,\"c_distance_actual\":1099000,\"ds_call_cnt_7d\":0,\"d_top1_line_cargo_rate_30d\":0.0,\"d_top1_truck_length_cargo_cnt_30d\":0,\"d_click_days_30d\":0,\"c_is_lcl_cargo\":0,\"d_avg_view_cnt_30d\":0,\"d_call_cargo_cnt_30d\":0,\"d_top1_end_c_end_distance\":0,\"d_base_credit_score\":0,\"d_top1_end_c_end_mileage\":0,\"s_is_active_today\":0,\"is_pre_same_truck_length\":0,\"d_view_cargo_cnt_30d\":0,\"d_second_category_cnt_30d\":0,\"d_install_city_id\":0,\"c_truck_length\":6.8,\"c_is_security_tran\":0,\"d_truck_length\":0.0,\"d_top1_gps_mileage\":0}");

        // 根据 ObjectMapper 生产对应的 String 参数
        String requestBody = serializeObject(listStr);
        System.out.println(requestBody);
    }
}
