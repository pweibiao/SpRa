package config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rabbitMQ.listener.QueueListener1;
import rabbitMQ.listener.QueueListener2;
import rabbitMQ.listener.SubscribeListener1;
import rabbitMQ.listener.SubscribeListener2;

//发布订阅模式的配置,包括两个队列和对应的订阅者,发布者的交换机类型使用fanout(子网广播),两根网线binding用来绑定队列到交换机
@Configuration
public class PublishSubscribeConfig {
	@Autowired
	RabbitConfig rabbitconfig;

 	@Bean
    public Queue myQueue1() {
       Queue queue=new Queue("queue1");
       return queue;
    }
 	
 	@Bean
    public Queue myQueue2() {
       Queue queue=new Queue("queue2");
       return queue;
    }
 	
 	@Bean
 	public FanoutExchange fanoutExchange(){
 		FanoutExchange fanoutExchange=new FanoutExchange("fanout");
 		return fanoutExchange;
 	}
 	
 	@Bean
 	public Binding binding1(){
 		Binding binding=BindingBuilder.bind(myQueue1()).to(fanoutExchange());
 		return binding;
 	}
 	
 	@Bean
 	public Binding binding2(){
 		Binding binding=BindingBuilder.bind(myQueue2()).to(fanoutExchange());
 		return binding;
 	}
 	
 	/*
 	 //订阅者监听器1
    @Bean
    public SubscribeListener1 subscribeListener1() {
    	return new SubscribeListener1();
    }
    
    @Bean
    public MessageListenerAdapter mailListenerAdapter() {
	    MessageListenerAdapter adapter = new MessageListenerAdapter();
	    adapter.setDelegate(subscribeListener1());
	    adapter.setDefaultListenerMethod("subscribe");
	    return adapter;
    }
    
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(rabbitconfig.connectionFactory());
	    container.setMessageListener(mailListenerAdapter());
	    container.setQueues(myQueue1());
	    return container;
    }
    
    //订阅者监听器2
    @Bean
    public SubscribeListener2 subscribeListener2() {
    	return new SubscribeListener2();
    }
    
    @Bean
    public MessageListenerAdapter mailListenerAdapter2() {
	    MessageListenerAdapter adapter = new MessageListenerAdapter();
	    adapter.setDelegate(subscribeListener2());
	    adapter.setDefaultListenerMethod("subscribe");
	    return adapter;
    }
    
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer2() {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(rabbitconfig.connectionFactory());
	    container.setMessageListener(mailListenerAdapter2());
	    container.setQueues(myQueue2());
	    return container;
    }
    */
}
