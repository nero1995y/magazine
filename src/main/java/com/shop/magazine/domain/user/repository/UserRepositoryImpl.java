package com.shop.magazine.domain.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.magazine.domain.user.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

import static com.shop.magazine.domain.user.entity.QUser.*;

public class UserRepositoryImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<User> search() {
        return queryFactory
                .select(user)
                .from(user)
                .where(user.username.eq("nero"))
                .fetch();
    }
}
