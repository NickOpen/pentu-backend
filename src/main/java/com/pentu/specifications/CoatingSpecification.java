package com.pentu.specifications;

import com.pentu.common.jpa.BaseSpecification;
import com.pentu.domain.Coatings;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CoatingSpecification extends BaseSpecification<Coatings> {
    private String name;

    public void setName(String name){
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Coatings> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(null != name){
            this.addAndPredicate(this.likePredicate(criteriaBuilder, this.name, root.get("name")));
        }

        return this.getAllPredicate(criteriaBuilder);
    }
}
