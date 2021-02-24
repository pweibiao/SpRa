package boot.spring.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    //定义一个pwb交换机
    @Bean
    public DirectExchange pwbDirectExchange(){
        DirectExchange myExchange = new DirectExchange("pwb");
        return myExchange;
    }

    //定义消息队列
    @Bean
    public Queue pengQueue(){
        Queue queue = new Queue("pengQueue");
        return queue;
    }

    //定义队列绑定
    @Bean
    public Binding bing1(){
        Binding binding = BindingBuilder.bind(pengQueue()).to(pwbDirectExchange()).with("pwb");
        return binding;
    }



}
