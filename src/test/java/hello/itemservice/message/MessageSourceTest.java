package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        String result = ms.getMessage("dd", null, null);
        assertThat(result).isEqualTo("??");
    }

    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(()-> ms.getMessage("no_code",null,null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no_code",null,"기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage(){
        String result = ms.getMessage("hello.name",new Object[]{"spring"},null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang(){
        assertThat(ms.getMessage("hello",null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
