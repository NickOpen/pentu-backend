package com.pentu.specifications;



import com.pentu.common.jpa.BaseSpecification;
import com.pentu.domain.CoatingDevice;
import com.pentu.domain.WorkingGasType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class WorkingGasTypeSpecification extends BaseSpecification<WorkingGasType> {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<WorkingGasType> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(null != name){
            this.addAndPredicate(this.likePredicate(criteriaBuilder, this.name, root.get("name")));
        }

        return this.getAllPredicate(criteriaBuilder);
    }
}