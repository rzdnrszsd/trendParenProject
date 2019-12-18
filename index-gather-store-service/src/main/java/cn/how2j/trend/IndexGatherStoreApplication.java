package cn.how2j.trend;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 代码采集
 * @author yangyonglong
 * @date 2019-12-18
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCaching
public class IndexGatherStoreApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(IndexGatherStoreApplication.class).run(args);
    }

    /**
     * 需要把这个类注册进来 indexService才能调用
     * @return
     */
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
