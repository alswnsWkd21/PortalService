package kr.ac.jejunu.hello;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

public class StaticApplicationContextTest {
    @Test
    //testcase상에서 쓰게된다. 코드상으로 빈등록
   public void staticApplicationContextTest(){
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello", HelloImpl.class);
        //bean등록할 때 코드로 직접하고 있다. 어노테이션이 아니라!! 근데 테스트할 때 가끔 쓰인다.
        Hello hello = applicationContext.getBean("hello", Hello.class);
        assertThat(hello.sayHello(), is("Hello!!!"));
   }
   @Test
   public void staticApplicationContextWithDI(){
        StaticApplicationContext applicationContext = new StaticApplicationContext();
        applicationContext.registerSingleton("hello", HelloImpl.class);
        //빈을 하나 정의한다.
        BeanDefinition beanDefinition = new RootBeanDefinition(HelloPerson.class);
        beanDefinition.getPropertyValues().addPropertyValue("name", "정민준");
        beanDefinition.getPropertyValues().addPropertyValue("hello", new RuntimeBeanReference("hello"));
        applicationContext.registerBeanDefinition("helloPerson", beanDefinition);
        Hello hello = applicationContext.getBean("helloPerson", Hello.class);
        assertThat(hello.sayHello(), is("Hello!!!정민준"));

   }
}
