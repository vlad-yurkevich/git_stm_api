package by.softclub.stm_api.hzconfig;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${spring.hazelcast.addresses}")
    private String addresses;

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig clientConfig = new ClientConfig();
        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();

        networkConfig.addAddress(addresses.split("#"))
                .setSmartRouting(true)
                .addOutboundPortDefinition("5801-5900")
                .setRedoOperation(true)
                .setConnectionTimeout(60000)
              //  .setAddresses(List.of(addresses))
        ;

        return clientConfig;
    }


    @Bean("hazelcastInstance")
    public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
        return HazelcastClient.newHazelcastClient(clientConfig);
    }
}
