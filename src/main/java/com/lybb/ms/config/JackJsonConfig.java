package com.lybb.ms.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/21 14:22 $
*/
@Configuration
public class JackJsonConfig {

    @Value("${spring.jackson.local-date-time-format}")
    String localDateTimeFormat;

    @Value("${spring.jackson.local-date-format}")
    String localDateFormat;

    @Value("${spring.jackson.local-time-format}")
    String localTimeFormat;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder.serializationInclusion(JsonInclude.Include.NON_NULL)
            .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(localDateTimeFormat)))
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .deserializerByType(LocalDateTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(localDateTimeFormat)));
    }



//    @Bean("objectMapper")
//    @Primary
//    public ObjectMapper objectMapper(){
//
//
//        ObjectMapper objectMapper =new ObjectMapper();
//
//        //null值设置为空字符串
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator,
//                                  SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeString("");
//
//            }
//        });
//
//
//        JavaTimeModule javaTimeModule = new JavaTimeModule();
//
//        //这里也没有生效??
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
//        SimpleModule simpleModule = new SimpleModule()
//            // 将Long类型/BigDecimal类型转成字符串类型
//            .addSerializer(Long.class, ToStringSerializer.instance)
//            .addSerializer(BigDecimal.class, ToStringSerializer.instance);
//
//        List<Module> moduleList = new ArrayList<>();
//        moduleList.add(javaTimeModule);
//        moduleList.add(simpleModule);
//
//        objectMapper.registerModules(moduleList);
//
//        //设置缩进
////        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        //蛇形命名法
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
//        // 不存在的字段,不被序列化
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        return objectMapper;
//
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(@Qualifier("objectMapper") ObjectMapper objectMapper) {
//
//        return new MappingJackson2HttpMessageConverter(objectMapper);
//    }

}
