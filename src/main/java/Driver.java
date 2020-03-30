import redis.clients.jedis.Jedis;

import java.util.Set;

public class Driver {

    public static void main(String[] args)
    {
        //Connecting to Redis server on localhost
//        Jedis jedis = new Jedis("localhost");
       Jedis jedis = RedisProvider.getInstance().getJedisPoolInstance();


        //check whether server is running or not
        if(jedis.ping().equals("PONG"))
        { System.out.println("Redis is running");
          jedis.set("data","purge");

            jedis.sadd("Request1", "123456789");
            jedis.sadd("Request1", "123456785");
            jedis.sadd("Request1", "444444444");


            Set<String> dataSet = jedis.smembers("Request1");
            dataSet.forEach(System.out::println);

//        jedis.get("son");

        }



    }
}
