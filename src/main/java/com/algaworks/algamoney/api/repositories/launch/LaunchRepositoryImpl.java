package com.algaworks.algamoney.api.repositories.launch;

import com.algaworks.algamoney.api.models.Launch;
import com.algaworks.algamoney.api.models.Launch_;
import com.algaworks.algamoney.api.repositories.filters.LaunchFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class LaunchRepositoryImpl implements LaunchRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Launch> filter(LaunchFilter launchFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Launch> criteriaQuery = builder.createQuery(Launch.class);
        Root<Launch> root = criteriaQuery.from(Launch.class);

        Predicate[] predicates = createRestrictions(launchFilter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Launch> query = manager.createQuery(criteriaQuery);
        addPaginationRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(launchFilter));
    }

    private Predicate[] createRestrictions(LaunchFilter launchFilter, CriteriaBuilder builder,
                                           Root<Launch> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(launchFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Launch_.description)), "%" + launchFilter.getDescription().toLowerCase() + "%"));
        } else {
            predicates.add(builder.like(builder.lower(root.get(Launch_.description)), "%"));
        }

        if (launchFilter.getExpirationDateOf() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Launch_.dueDate), launchFilter.getExpirationDateOf()));
        }

        if (launchFilter.getExpirationDateUntil() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Launch_.dueDate), launchFilter.getExpirationDateUntil()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPaginationRestrictions(TypedQuery<Launch> query, Pageable pageable) {
        int actualPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstRecordPage = actualPage * totalRecordsPerPage;

        query.setFirstResult(firstRecordPage);
        query.setMaxResults(totalRecordsPerPage);
    }

    private Long total(LaunchFilter launchFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Launch> root = criteriaQuery.from(Launch.class);

        Predicate[] predicates = createRestrictions(launchFilter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }
}
