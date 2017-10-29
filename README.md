# myredis
same like mybatis for  NoSQL database

#示例


import cn.kszeng.enums.TimeEnum;

import cn.kszeng.redis.RedisSession;

import cn.kszeng.redis.RedisSessionFactory;

import cn.kszeng.redis.RedisSessionFactoryBuilder;

import org.junit.Test;

/**
 * @author Bryan Sun
 * @version V1.0
 * @e-mail bryansun@163.com
 * @date 2017/10/24 20:34
 * @Description:
 */

public class RedisTest {

    @Test
    public void test1() {
        RedisSessionFactory factory = new RedisSessionFactoryBuilder()
                .build("120.24.51.133", 6379, "yhysj");
        RedisSession session = factory.openSession();
        String value = session.getJedis().get("name");
        System.out.println(value);
    }

    @Test
    public void test2() {
        RedisSessionFactory factory = new RedisSessionFactoryBuilder()
                .build("120.24.51.133","password");
        RedisSession session = factory.openSession();
        String value = session.getJedis().get("name");
        System.out.println(value);
    }

    @Test
    public void test3() {
        RedisSessionFactory factory = new RedisSessionFactoryBuilder()
                .build("120.24.51.133","password");
        RedisSession session = factory.openSession();
        String value = session.selectFromDB(0,"name");
        System.out.println(value);
    }

    @Test
    public void test4() {
        RedisSessionFactory factory = new RedisSessionFactoryBuilder()
                .build("120.24.51.133","password");
        RedisSession session = factory.openSession();
        session.setDataExpire("name",10, TimeEnum.SECOND);
    }

    @Test
    public void test5() {
        RedisSessionFactory factory = new RedisSessionFactoryBuilder()
                .build("120.24.51.133","password");
        RedisSession session = factory.openSession();
        session.insertString("name","xxx",true);
        session.insertString("name","kslive@yeah.net",false);
        System.out.println(session.selectFromDB(0,"name"));
    }

    @Test
    public void test6(){
        RedisSessionFactory factory = new RedisSessionFactoryBuilder()
                .parseConfig("redis.properties").build();
        RedisSession session = factory.openSession();
        String value = session.selectFromDB(0,"name");
        System.out.println(value);
    }
}
