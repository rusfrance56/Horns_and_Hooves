package com.rest_jpa.repository;

import com.rest_jpa.entity.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskRepositoryImpl implements TaskRepositoryCustom{

    private EntityManager em;

    public TaskRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Task> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> adminAuthorities = authentication.getAuthorities().stream()
                .filter(a -> a.getAuthority().equals("ROLE_ADMIN"))
                .collect(Collectors.toList());

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(Task.class);
        CriteriaQuery<Task> query = criteriaQuery.select(taskRoot);
        if (authentication.isAuthenticated() && adminAuthorities.isEmpty()) {
            query = query.where(criteriaBuilder.equal(
                    taskRoot.get("user").get("logonName"), authentication.getName()
            ));
        }
        return em.createQuery(query).getResultList();
    }
}
