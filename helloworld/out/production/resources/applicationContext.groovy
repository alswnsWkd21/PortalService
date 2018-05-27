import kr.ac.jejunu.hello.Hello
import kr.ac.jejunu.hello.HelloImpl
import kr.ac.jejunu.hello.HelloPerson

beans {
    hello(HelloImpl) {
    }
    helloPerson(HelloPerson) {
        name = '정민준'
        hello = hello
    }
}