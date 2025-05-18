package com.example.currency_exchange_service.currency_exchange_service.repository;

import com.example.currency_exchange_service.currency_exchange_service.entity.CurrencyExchange;
import java.util.*;

public interface CurrencyExchangeRepository {
    CurrencyExchange findById(int id);
    List<CurrencyExchange> findByToAndFrom(String from, String to);
}
