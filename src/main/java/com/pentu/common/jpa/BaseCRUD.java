package com.pentu.common.jpa;


import com.pentu.common.dto.PagingList;
import com.bx.util.jpa.EnhancedBeanUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public abstract class BaseCRUD<Domain, Repository extends PagingAndSpecificationRepository> {

    protected Repository repository;


    @Autowired
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    /**
     * 条件查询 Entity 结果集,带分页功能
     *
     * @param specification
     * @param pageable
     * @return
     */
    public PagingList<Domain> getDomainPagingResult(Specification specification, Pageable pageable) {
        List<Domain> content = getDomainResult(specification, pageable);
        PagingList<Domain> result = new PagingList<>();
        result.setData(content);
        result.setTotalNumbers(count(specification));
        return result;
    }

    /**
     * 条件查询 Entity 结果集, 不带分页功能
     *
     * @param specification
     * @return
     */
    public Optional<Domain> getOneDomainResult(Specification specification) {
        return repository.findOne(specification);
    }

    /**
     * 条件查询 Entity 结果集, 不带分页功能
     *
     * @param specification
     * @return
     */
    public List<Domain> getDomainResult(Specification specification) {
        return repository.findAll(specification);
    }

    /**
     * 条件查询 Entity 结果集, 不带分页功能, 带排序功能
     *
     * @param specification
     * @return
     */
    public List<Domain> getDomainResult(Specification specification, Sort sort) {
        return repository.findAll(specification, sort);
    }

    /**
     * 条件计算 Entity 结果数目
     *
     * @param specification
     * @return
     */
    public long count(Specification specification) {
        return repository.count(specification);
    }

    /**
     * 查询一条 Entity 记录
     *
     * @param id
     * @return
     */
    public Domain findOneDomain(Integer id) {
        Optional<Domain> optional = repository.findById(id);
        return optional.orElse(null);
    }


    /**
     * 添加 Entity 记录
     *
     * @param domain
     * @return
     */
    public Domain addDomain(Domain domain) {
        Domain result;
        result = (Domain) repository.save(domain);
        return result;
    }

    public void saveAllDomain(Collection<Domain> domains){
        repository.saveAll(domains);
    }

    /**
     * 添加 Entity 集合
     *
     * @param domain
     * @return
     */
    public void addDomain(Iterable<Domain> domain) {
        repository.saveAll(domain);
    }


    public Domain updateDomain(Domain domain, Integer id) {
        Domain exist = findOneDomain(id);
        EnhancedBeanUtils.copyNotNullProperties(domain, exist);
        return addDomain(domain);
    }

    public void deleteDomain(Integer id) {
        Domain domain = findOneDomain(id);
        repository.delete(domain);
    }

    public List<Domain> getDomainResult(Specification specification, Pageable pageable) {
        return repository.findAll(specification, pageable).getContent();
    }

    /**
     * 获取所有Domain
     *
     * @return
     */
    public List<Domain> getAllDomain() {
        return IterableUtils.toList(repository.findAll());
    }


    /**
     * 保存 Domain
     *
     * @param domain
     * @return
     */
    public Domain saveDomain(Domain domain) {
        return (Domain) repository.save(domain);
    }

    /**
     * 添加 Entity 集合
     *
     * @param domain
     * @return
     */
    public void saveDomain(Iterable<Domain> domain) {
        repository.saveAll(domain);
    }

}
