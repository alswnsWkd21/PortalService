package kr.ac.jejunu.hello;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResourceApplicationContextTest {
    @Test
    public void classpathXmlApplicationContextTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Hello hello = applicationContext.getBean("helloPerson", Hello.class);
        assertThat(hello.sayHello(), is("Hello!!!정민준"));
    }

    @Test
    public void groovyApplicationContextTest(){
        GenericGroovyApplicationContext applicationContext = new GenericGroovyApplicationContext("applicationContext.groovy");
        Hello hello = applicationContext.getBean("helloPerson", Hello.class);
        assertThat(hello.sayHello(), is("Hello!!!정민준"));
    }

}
