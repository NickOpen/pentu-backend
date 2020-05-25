package com.pentu.common.jpa;

import com.pentu.common.dto.PagingList;
import com.pentu.utils.EnhancedBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.collections4.IterableUtils;

import java.util.Optional;
import java.util.function.*;
import java.util.*;

public abstract class BasePASCrud<Domain, Repository extends CombinedPASRepository> {
    protected Repository repository;

    @Autowired
    public void setRepository(Repository repository){
        this.repository = repository;
    }

    /******************************
     **************查询*************
     ******************************/

    /**
     * 按照specification条件查询Entity结果集，不带分页功能
     * @param specification
     * @return
     */
    public Optional<Domain> getOneDomainResult(Specification specification){
        return repository.findOne(specification);
    }

    /**
     * 按照specification条件查询Entity结果集，不带分页&排序功能
     * @param specification
     * @return
     */
    public List<Domain> getDomainResult(Specification specification){
        return repository.findAll(specification);
    }

    /**
     * 按照specification条件查询Entity结果集，不带分页，带排序功能
     * @param specification
     * @return
     */
    public List<Domain> getDomainResult(Specification specification, Sort sort){
        return repository.findAll(specification, sort);
    }

    /**
     * 按照specification和Pageable条件查询Entity结果集，带分页，不带排序功能
     * @param specification
     * @param pageable
     * @return
     */
    public List<Domain> getDomainResult(Specification specification, Pageable pageable){
        return repository.findAll(specification, pageable).getContent();
    }

    /**
     * 获取所有Entity
     * @return
     */
    public List<Domain> getAllDomain(){
        return IterableUtils.toList(repository.findAll());
    }

    /**
     * 根据ID查询指定的一条记录
     * @param id
     * @return
     */
    public Domain findOneDomain(Integer id){
        Optional<Domain> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public PagingList<Domain> getDomainPagingResult(Specification specification, Pageable pageable){
        List<Domain> ret = getDomainResult(specification, pageable);
        PagingList<Domain> result = new PagingList<>();
        result.setData(ret);
        result.setSize(count(specification));

        return result;
    }

    /**
     * 条件计算 Entity 结果数目
     *
     * @param specification
     * @return
     */
    public long count(Specification specification){
        return repository.count(specification);
    }


    /*******************************
     ************** 修改 ***********
     ******************************/
    public Domain addDomain(Domain domain){
        return (Domain)repository.save(domain);
    }

    public void saveAllDomain(Collection<Domain> domains){
        repository.saveAll(domains);
    }

    public void addDomain(Iterable<Domain> domain){
        repository.saveAll(domain);
    }

    public Domain updateDomain(Domain domain, Integer id){
        Domain exist = findOneDomain(id);
        EnhancedBeanUtils.copyNotNullProperties(domain, exist);
        return addDomain(domain);
    }

    public Domain saveDomain(Domain domain){
        return (Domain)repository.save(domain);
    }

    public void saveAllDomain(Iterable<Domain> domain){
        repository.saveAll(domain);
    }

    public Boolean isExistedById(Integer id){
       return repository.existsById(id);
    }

    public void deleteDomain(Domain domain){
        repository.delete(domain);
    }

    /**********************************
     ************** 工具函数 ***********
     **********************************/
    public Collection domain2DtoConvert(Collection<Domain> domainCollection,
                                        Collection targetCollection,
                                        Function<Domain, Object> convertFun){
        for(Domain src: domainCollection){
            Object target = convertFun.apply(src);
            targetCollection.add(target);
        }

        return targetCollection;
    }

    public static void copyNotNullProperties(Object source, Object target, String... ignoreProperties){
        EnhancedBeanUtils.copyNotNullProperties(source, target, ignoreProperties);
    }

    public static Object copyNotNullProperties(Object source, Class targetClass,String... ignoreProperties){
        return EnhancedBeanUtils.copyNotNullProperties(source, targetClass, ignoreProperties);
    }

    public abstract Object domain2DTO(Domain domain);
}
