package com.billy.controller;

import com.billy.service.WeatherReportService;
import com.billy.vo.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 天气预报控制层
 * @author BillyYang
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherReportService reportService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception{

        // TODO 修改为通过城市数据API服务来提供服务

        List<City> cityList = null;

        try {
            // TODO 调用城市数据API
            cityList = new ArrayList<>();
            City city = new City();
            city.setCityId("101280601");
            city.setCityName("深圳");
            cityList.add(city);

            city = new City();
            city.setCityId("101280301");
            city.setCityName("惠州");
            cityList.add(city);
        } catch (Exception e) {
            logger.error("获取城市信息异常", e);
            throw new RuntimeException("获取城市信息异常", e);
        }

        model.addAttribute("title", "天气预报");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityList);
        model.addAttribute("report", reportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);
    }
}
