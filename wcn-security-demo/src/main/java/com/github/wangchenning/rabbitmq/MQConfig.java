package com.github.wangchenning.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MQConfig {


	/**
	 * 消息交换机的名字
	 * */
	private static final String DIRECT_EXCHANGE = "DirectExchange";

	private static final String TOPIC_EXCHANGE = "TopicExchange";

	private static final String FANOUT_EXCHANGE ="FanoutExchange" ;

	private static final String HEADERS_EXCHANGE ="HeadersExchange" ;

	/**
	 * 队列的名字
	 * */
	private static final String DIRECT_QUEUE = "DirectQueue";

	private static final String TOPIC_QUEUE = "TopicQueue";

	private static final String FANOUT_QUEUE = "FanoutQueue";

	private static final String HEADERS_QUEUE = "HeadersQueue";

	/**
	 * key
	 * */
	private static final String DIRECT_KEY = "DirectKey";

	private static final String TOPIC_KEY = "Topic.#";
	/**
	 * 1.队列名字
	 * 2.durable="true" 是否持久化 rabbitmq重启的时候不需要创建新的队列
	 * 3.auto-delete    表示消息队列没有在使用时将被自动删除 默认是false
	 * 4.exclusive      表示该消息队列是否只在当前connection生效,默认是false
	 */
	@Bean
	public Queue dirctQueue() {
		return new Queue(DIRECT_QUEUE,true,false,false);
	}

	@Bean
	public Queue topicQueue() {
		return new Queue(TOPIC_QUEUE,true,false,false);
	}


	@Bean
	public Queue fanoutQueue() {
		return new Queue(FANOUT_QUEUE,true,false,false);
	}

	@Bean
	public Queue headersQueue() {
		return new Queue(HEADERS_QUEUE,true,false,false);
	}
	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange(DIRECT_EXCHANGE,true,false);
	}

	/**
	 * 1.交换机名字
	 * 2.durable="true" 是否持久化 rabbitmq重启的时候不需要创建新的交换机
	 * 3.autoDelete    当所有消费客户端连接断开后，是否自动删除队列
	 */
	@Bean
	public TopicExchange topicExchange(){
		return new TopicExchange(TOPIC_EXCHANGE,true,false);
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE,true,false);
	}

	@Bean
	public HeadersExchange headersExchange() {
		return new HeadersExchange(HEADERS_EXCHANGE,true,false);
	}

	/**
	 * 将direct队列和交换机进行绑定
	 */
	@Bean
	public Binding bindingDirect() {
		return BindingBuilder.bind(dirctQueue()).to(directExchange()).with(DIRECT_KEY);
	}

	@Bean
	public Binding bindingTopic() {
		return BindingBuilder.bind(topicQueue()).to(topicExchange()).with(TOPIC_KEY);
	}


	@Bean
	public Binding bindingFanout() {
		return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
	}

	@Bean
	public Binding headersBinding(){
		Map<String,Object> map = new HashMap<>();
		map.put("headers1","value1");
		map.put("headers2","value2");
		return BindingBuilder.bind(headersQueue()).to(headersExchange()).whereAll(map).match();
	}

	/**
	 * 定义消息转换实例  转化成 JSON 传输  传输实体就可以不用实现序列化
	 * */
	@Bean
	public MessageConverter integrationEventMessageConverter() {
		return  new Jackson2JsonMessageConverter();
	}
}
