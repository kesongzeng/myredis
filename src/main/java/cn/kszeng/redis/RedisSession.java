package cn.kszeng.redis;

import cn.kszeng.enums.TimeEnum;
import cn.kszeng.util.TimeUtil;
import redis.clients.jedis.Jedis;

/**
 * @author Bryan Sun
 * @version V1.0
 * @e-mail bryansun@163.com
 * @date 2017/10/24 20:18
 * @Description:
 */
public class RedisSession {
    private static final int DEFAULT_PORT = 6379;
    private Jedis jedis;

    public RedisSession() {
    }

    public RedisSession(String host) {
        jedis = new Jedis(host, DEFAULT_PORT);
    }

    public RedisSession(String host, String password) {
        jedis = new Jedis(host, DEFAULT_PORT);
        authorization(password);
    }

    public RedisSession(String host, int port, String password) {
        jedis = new Jedis(host, port);
        authorization(password);
    }

    private void authorization(String password) {
        jedis.auth(password);
    }

    public String selectFromDB(int dbID, String key) {
        jedis.select(dbID);
        return jedis.get(key);
    }

    public void insertString(String key, String value, boolean replacable) {
        if (replacable) {
            jedis.set(key, value);
            return;
        }
        jedis.setnx(key, value);
    }

    public void setDataExpire(String key,int time, TimeEnum timeEnum){
        int seconds = 0;
        switch (timeEnum){
            case DAY:
                seconds = TimeUtil.dayToSeconds(time);
                break;
            case HOUR:
                seconds = TimeUtil.hourToSeconds(time);
                break;
            case MINUTE:
                seconds = TimeUtil.minuteToSeconds(time);
                break;
            case SECOND:
                seconds = time;
                break;
        }
        jedis.expire(key,seconds);
    }

    public Jedis getJedis() {
        //jedis.set
        return jedis;
    }
}
