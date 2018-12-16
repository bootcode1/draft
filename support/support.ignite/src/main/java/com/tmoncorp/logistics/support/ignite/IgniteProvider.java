package in.java.support.ignite;

import in.java.support.util.Config;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.AddressResolver;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.NearCacheConfiguration;
import org.apache.ignite.lang.IgniteCallable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by bootcode on 2018-07-04.
 */
public class IgniteProvider {

    static {
        Config config = Config.of("classpath://config/config.yaml");
        String file = config.getString("support.ignite.config.file", null);
        if(file != null) {
            IGNITE = Ignition.start(file);
        }else {
            IGNITE = Ignition.start();
        }
    }

    private static Ignite IGNITE;

    private IgniteProvider() {
    }

    public static Ignite connect() {
        return IGNITE;
    }

    public static boolean close() {
        return Ignition.stop(true);
    }

}
