package com.algaworks.algamoney.api.repositories.person;

import com.algaworks.algamoney.api.models.Person;
import com.algaworks.algamoney.api.models.Person_;
import com.algaworks.algamoney.api.repositories.filters.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Person> filter(PersonFilter personFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        Predicate[] predicates = createRestrictions(personFilter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Person> query = manager.createQuery(criteriaQuery);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(personFilter));
    }

    private Predicate[] createRestrictions(PersonFilter personFilter, CriteriaBuilder builder,
                                           Root<Person> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(personFilter.getName())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Person_.name)), "%" + personFilter.getName().toLowerCase() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPaginationRestrictions(TypedQuery<?> query, Pageable pageable) {
        int actualPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstRecordPage = actualPage * totalRecordsPerPage;

        query.setFirstResult(firstRecordPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(PersonFilter personFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        Predicate[] predicates = createRestrictions(personFilter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
