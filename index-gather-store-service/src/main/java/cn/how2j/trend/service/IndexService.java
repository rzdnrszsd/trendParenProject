package cn.how2j.trend.service;

import cn.how2j.trend.pojo.Index;
import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * cacheNames = "indexes" 表示缓存的名称就是 indexes
 */
@Service
@CacheConfig(cacheNames = "indexes")
public class IndexService {
    private List<Index> indexList;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 表示缓存的key=all_codes
     * @return
     */
    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    @Cacheable(key = "'all_codes'")
    public List<Index> fetch_indexes_from_third_part(){
       List<Map> maps =restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json",List.class);
        return map2Index(maps);
    }

    public List<Index> third_part_not_connected(){
        System.out.println("third_part_not_connected()");
        Index index = new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return CollectionUtil.toList(index);
    }

    private List<Index> map2Index(List<Map> maps) {
        List<Index> indexes = new ArrayList<>();
        for (Map map : maps) {
            String code = map.get("code").toString();
            String name = map.get("name").toString();
            Index index= new Index();
            index.setCode(code);
            index.setName(name);
            indexes.add(index);
        }

        return indexes;
    }

}
