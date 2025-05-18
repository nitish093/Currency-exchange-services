package com.example.currency_exchange_service.currency_exchange_service.controller;

import com.example.currency_exchange_service.currency_exchange_service.entity.CurrencyExchange;
import com.example.currency_exchange_service.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.function.Consumer;

@RestController
public class CurrencyExchangeController {


    private Environment environment;
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public List<CurrencyExchange> retrieveExchangeValue(@PathVariable("from") String currencyFrom, @PathVariable("to") String currencyTo)
    {
        logger.info("Inside the retrieveExchangeValue functions");
        String property = environment.getProperty("local.server.port");

        List<CurrencyExchange> byToAndFrom = currencyExchangeRepository.findByToAndFrom(currencyFrom, currencyTo);
        Consumer<? super CurrencyExchange> consumer = con ->{
            con.setEnvironment(property);
        };

        byToAndFrom.stream()
                .forEach(consumer);

        byToAndFrom.stream()
                .forEach(x -> logger.info(x.toString()));
        return byToAndFrom;

    }

}
