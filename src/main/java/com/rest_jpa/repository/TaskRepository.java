package com.rest_jpa.repository;

import com.rest_jpa.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {

    /*@Query("select task from Task task where task.user.userName like " +
            "?#{hasRole('ROLE_ADMIN') ? '%' : principal.username}")
    @Override
    List<Task> findAll();*/

    /*
    @Query("select o from BusinessObject o where o.owner.id = ?#{principal.id} or 1=?#{hasRole('ROLE_ADMIN') ? 1 : 0}")
    List<BusinessObject> findBusinessObjectsForCurrentUserById();


    @Modifying(clearAutomatically = true)
    @Query("update BusinessObject b set b.data = upper(b.data), b.lastModifiedBy = :#{#security.principal}, b.lastModifiedDate = :#{new java.util.Date()}")
    void modifiyDataWithRecordingSecurityContext();
    */
}
