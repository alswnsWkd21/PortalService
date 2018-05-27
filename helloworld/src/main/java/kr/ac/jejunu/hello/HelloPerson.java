package kr.ac.jejunu.hello;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class HelloPerson implements Hello{
    @Value("정민준")
    private String name;
    private final Hello hello;

    @Override
    public String sayHello() {
        return hello.sayHello()+ name;
    }
}
