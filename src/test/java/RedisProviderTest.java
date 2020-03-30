import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RedisProviderTest {

    @Test
    public void itShouldReturnJedisInstanceFromPool()
    {
        Jedis jedis = RedisProvider.getInstance().getJedisPoolInstance();
        assertNotNull(jedis);
    }
}
