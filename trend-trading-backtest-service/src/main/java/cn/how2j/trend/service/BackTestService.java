package cn.how2j.trend.service;

import cn.how2j.trend.client.IndexDataClient;
import cn.how2j.trend.pojo.IndexData;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author yangyonglong
 */
@Service
public class BackTestService {

    @Autowired
    IndexDataClient indexDataClient;

    public List<IndexData> getIndexDataByCode(String code){
        List<IndexData> indexDataList = Lists.newArrayList();
        indexDataList = indexDataClient.getIndexData(code);
        if(!CollectionUtils.isEmpty(indexDataList)){
            Collections.reverse(indexDataList);

            indexDataList.forEach(indexData->{
                System.out.println(indexData.getDate());
            });
        }

        return indexDataList;
    }
}
