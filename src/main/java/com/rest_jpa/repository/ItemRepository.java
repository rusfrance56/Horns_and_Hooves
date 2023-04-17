package com.rest_jpa.repository;

import com.rest_jpa.entity.Item;
import com.rest_jpa.entity.Item_;
import com.rest_jpa.enumTypes.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    @Query(value = "SELECT i FROM Item i WHERE lower(i.name) like lower(concat('%', :filter, '%')) " +
            "OR lower(i.description) like lower(concat('%', :filter, '%'))")
    Page<Item> findPageByNameOrDescription(String filter, Pageable pageable);

    interface Specs {
        static Specification<Item> byDepartment(Department department) {
            return (root, query, builder) -> department == null
                    ? null
                    : builder.equal(root.get(Item_.department), department);
        }

        static Specification<Item> byPriceGreaterThanEqual(Double price) {
            return (root, query, builder) -> price == null
                    ? null
                    : builder.greaterThanOrEqualTo(root.get(Item_.price), price);
        }
/*
        static Specification<Item> byReviewLike(String reviewPattern) {
            return (root, query, builder) ->
                    builder.like(root.get(Item_.review), reviewPattern);
        }*/

        static Specification<Item> orderByUpdated(Specification<Item> spec) {
            return (root, query, builder) -> {
                query.orderBy(builder.asc(root.get(Item_.updated)));
                return spec.toPredicate(root, query, builder);
            };
        }
    }
}
