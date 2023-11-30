package br.com.seteideias.webfluxjava.router;

import br.com.seteideias.webfluxjava.handler.CustomerHandler;
import br.com.seteideias.webfluxjava.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.function.Consumer;

import static java.lang.System.out;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){

        Consumer<String> stringConsumer1 = e -> out.println(e);
        Consumer<String> stringConsumer2 = out::println;

        stringConsumer2.accept("sei la!");


        return RouterFunctions.route()
                .GET("/router/customers",handler::loadCustomers)
                .GET("/router/customers/stream",streamHandler::getCustomers)
                .GET("/router/customer/{input}",handler::findCustomer)
                .POST("/router/customer/save",handler::saveCustomer)
                .build();

    }
}
