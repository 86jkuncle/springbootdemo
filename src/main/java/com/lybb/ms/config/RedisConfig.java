package com.lybb.ms.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description redis配置类
 * @Author mtxst
 * @Date 2024/3/13 17:41 $
 */
@Configuration
public class RedisConfig {


    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory,ObjectMapper objectMapper) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer); // key的序列化类型

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value的序列化类型
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Value("${spring.jackson.local-date-time-format:yyyy-MM-dd HH:mm:ss}")
    String localDateTimeFormat;

    @Value("${spring.jackson.local-date-format:yyyy-MM-dd}")
    String localDateFormat;

    @Value("${spring.jackson.local-time-format:HH:mm:ss}")
    String localTimeFormat;


    //下面这个配置没有生效
//    public Jackson2JsonRedisSerializer<Object> serializer() {
//        //设置序列化
//        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator,
//                                  SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeString("");
//
//            }
//        });
//
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(
//            DateTimeFormatter.ofPattern(localDateTimeFormat)
//        ));
//
//        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(
//            DateTimeFormatter.ofPattern(localDateFormat)
//        ));
//
//        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(
//            DateTimeFormatter.ofPattern(localTimeFormat)
//        ));
//
//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
//            DateTimeFormatter.ofPattern(localDateTimeFormat)));
//
//        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(
//            DateTimeFormatter.ofPattern(localDateFormat)
//        ));
//        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(
//            DateTimeFormatter.ofPattern(localTimeFormat)
//        ));
//        objectMapper.registerModule(javaTimeModule);
//
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
//        // 不存在的字段,不被序列化
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        return jackson2JsonRedisSerializer;
//    }
//
//    作者：一缕82年的清风
//    链接：https://juejin.cn/post/7076244567569203208
//    来源：稀土掘金
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
