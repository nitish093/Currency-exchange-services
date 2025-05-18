package com.example.currency_exchange_service.currency_exchange_service.repository;

import com.example.currency_exchange_service.currency_exchange_service.entity.CurrencyExchange;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyExchangeRepositoryImpl implements CurrencyExchangeRepository{

    private EntityManager entityManager;

    @Autowired
    public CurrencyExchangeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CurrencyExchange findById(int id) {

        CurrencyExchange currencyExchange = entityManager.find(CurrencyExchange.class,id);
        return currencyExchange;
    }

    @Override
    public List<CurrencyExchange> findByToAndFrom(String currencyFrom, String currencyTo) {

        TypedQuery<CurrencyExchange> typedQuery = entityManager.createQuery("Select c from CurrencyExchange c where currencyTo = :currencyTo and currencyFrom = :currencyFrom",CurrencyExchange.class);
        typedQuery.setParameter("currencyFrom",currencyFrom);
        typedQuery.setParameter("currencyTo",currencyTo);

        List<CurrencyExchange> list = typedQuery.getResultList();
        return list;
    }
}
