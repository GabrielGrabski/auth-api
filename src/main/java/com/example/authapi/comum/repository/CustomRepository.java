package com.example.authapi.comum.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomRepository {

    @PersistenceContext
    protected EntityManager entityManager;
}
