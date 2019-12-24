package cn.how2j.trend.web;

import cn.how2j.trend.pojo.IndexData;
import cn.how2j.trend.service.BackTestService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BackTestController {

    @Autowired
    BackTestService backTestService;

    @GetMapping("/simulate/{code}")
    public Map<String,Object> backTest(@PathVariable("code") String code){
        List<IndexData> indexDataList = backTestService.getIndexDataByCode(code);
        Map<String,Object> result = Maps.newHashMap();
        result.put("indexDatas",indexDataList);
        return  result;
    }
}
