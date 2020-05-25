package com.pentu.common.jpa;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public abstract class BaseSpecification<T> implements Specification<T> {
    protected T domainObject;

    private List<Predicate> andPredicates = new ArrayList<>();
    private List<Predicate> orPredicates = new ArrayList<>();

    public BaseSpecification(){}

    public BaseSpecification(T domainObject){
        this.domainObject = domainObject;
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb){
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Predicate orTogether(List<Predicate> predicates, CriteriaBuilder cb){
        return cb.or(predicates.toArray(new Predicate[predicates.size()]));
    }

    private Predicate getOrPredicate(CriteriaBuilder cb) {
        Predicate predicate = orTogether(orPredicates, cb);
        orPredicates = new ArrayList<>();
        return predicate;
    }

    private Predicate getAndPredicate(CriteriaBuilder cb) {
        Predicate predicate = andTogether(andPredicates, cb);
        andPredicates = new ArrayList<>();
        return predicate;
    }

    protected void addAndPredicate(Predicate predicate){
        this.andPredicates.add(predicate);
    }

    protected void addOrPredicate(Predicate predicate){
        this.orPredicates.add(predicate);
    }

    protected Predicate likePredicate(CriteriaBuilder cb, String keywords, Expression<String> expression){
        return cb.like(cb.lower(expression), "%" + keywords.toLowerCase() + "%");
    }

    protected Predicate getAllPredicate(CriteriaBuilder cb){
        if(this.andPredicates.size() > 0 && orPredicates.size() > 0){
            return cb.and(getAllPredicate(cb), getOrPredicate(cb));
        }else if(this.andPredicates.size() > 0){
            return getAndPredicate(cb);
        }else if(this.orPredicates.size() > 0){
            return getOrPredicate(cb);
        }else {
            return null;
        }
    }
}
