import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

public class RedisProvider {

    private static RedisProvider single_instance=null;
    JedisPool pool;
    private String host="localhost";
    int timeout=5000;
    private int port=6379; //defaults to redis port
    private String password="";

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxActive(500);
        poolConfig.setMaxIdle(128);
      //  poolConfig.setMinIdle(16);
//        poolConfig.setTestOnBorrow(true);
//        poolConfig.setTestOnReturn(true);
//        poolConfig.setTestWhileIdle(true);
//        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
//        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
//        poolConfig.setNumTestsPerEvictionRun(3);
        return poolConfig;
    }
    private RedisProvider()
    {
        pool = new JedisPool(buildPoolConfig(), host,port);
    }
    public static synchronized RedisProvider getInstance()
    {
        if (single_instance == null)
        {

            single_instance = new RedisProvider();
        }
        return single_instance;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void destroyPool()
    {
      pool.destroy();
    }
    public Jedis getJedisPoolInstance()
    {
        Jedis jedis = null;
        try {
          jedis = pool.getResource();
          }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return jedis;
    }
}
