package kr.ac.jejunu.hello;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class HelloPerson implements Hello{
    private String name;
    private Hello hello;

    @Override
    public String sayHello() {
        return hello.sayHello()+ name;
    }
}
