package cn.how2j.trend.client;

import cn.how2j.trend.pojo.IndexData;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author yangyonglong
 */
@Component
public class IndexDataClientFeignHystrix implements IndexDataClient{


    @Override
    public List<IndexData> getIndexData(String code) {
        List<IndexData> indexDataList = Lists.newArrayList();
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("0000-00-00");
        indexDataList.add(indexData);
        return indexDataList;
    }
}
