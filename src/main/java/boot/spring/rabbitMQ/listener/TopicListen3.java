package boot.spring.rabbitMQ.listener;

import boot.spring.po.Mail;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicListen3 {
    @RabbitListener(queues = "topicqueue3")
    public void receive(Mail mail ){
        System.out.println("从topicqueue3取出消息Mail"+mail.toString());
    }
}
