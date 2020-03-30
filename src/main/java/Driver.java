import redis.clients.jedis.Jedis;

import java.util.Set;

public class Driver {

    public static void main(String[] args)
    {
       Jedis jedis = RedisProvider.getInstance().getJedisPoolInstance();
       //check whether server is running or not
        if(jedis.ping().equals("PONG"))
        { System.out.println("Redis is running");
          jedis.set("data","purge");

            jedis.sadd("setKey", "123456789");
            jedis.sadd("setKey", "123456785");
            jedis.sadd("setKey", "444444444");

            Set<String> dataSet = jedis.smembers("Request1");
            dataSet.forEach(System.out::println);
        }
    }
}
