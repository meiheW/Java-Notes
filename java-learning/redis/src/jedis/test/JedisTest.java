package jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author meihewang
 * @date 2019/08/10  11:28
 */
public class JedisTest {

    @Test
    public void test1() throws InterruptedException {
        //conn
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.set("username", "zhangsan");

        jedis.setex("exkey", 10, "exvalue");

        while (jedis.get("exkey")!=null && !jedis.get("exkey").equals("null")){

            String username = jedis.get("username");
            System.out.println(username);
            System.out.println(jedis.get("exkey"));
            Thread.sleep(1000);
        }
        jedis.close();
    }


    @Test
    public void test2() throws InterruptedException {
        //conn
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.hset("mhash", "name", "wmh");
        jedis.hset("mhash", "age", "26");

        System.out.println(jedis.hget("mhash", "name"));
        System.out.println(jedis.hget("mhash", "age"));

        Map<String, String> mhash = jedis.hgetAll("mhash");
        for (String key : mhash.keySet()) {
            System.out.println(mhash.get(key));
        }


        jedis.close();
    }


    @Test
    public void test3() throws InterruptedException {
        //conn
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.ltrim("mlist", 1,0);
        jedis.lpush("mlist", "a","b","c");
        jedis.rpush("mlist", "d","e","f");

        List<String> mlist = jedis.lrange("mlist", 0, -1);

        System.out.println(mlist);

        jedis.close();
    }


    @Test
    public void test4() throws InterruptedException {
        //conn
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.sadd("mset", "k1");
        jedis.sadd("mset", "k2");
        jedis.sadd("mset", "k3");

        Set<String> mset = jedis.smembers("mset");
        System.out.println(mset);
        jedis.close();
    }

    @Test
    public void test5() throws InterruptedException {
        //conn
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.zadd("zset", 80, "k1");
        jedis.zadd("zset", 90, "k2");
        jedis.zadd("zset", 60, "k3");

        Set<String> zset = jedis.zrange("zset", 0, -1);
        System.out.println(zset);
        jedis.close();
    }


    @Test
    public void test6() {
        //conn
        JedisPool jedisPool = new JedisPool();
        Jedis jedis = jedisPool.getResource();
        jedis.set("anothername", "wmh");
        jedis.close();
    }
}
