package json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author meihewang
 * @date 2019/07/29  23:10
 */
public class JsonTest {

    @Test
    public void myJsonTest(){

        Person p = new Person();
        p.setName("张三");
        p.setAge(11);
        p.setGender("男");

        ObjectMapper objMapper = new ObjectMapper();

        String s=null;
        try {
            s = objMapper.writeValueAsString(p);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("p对象转换为Json字符串为：" + s);
        Person pFromJString=null;
        try {
            pFromJString = objMapper.readValue(s, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("pFromJString为："+pFromJString.toString());


    }
}
