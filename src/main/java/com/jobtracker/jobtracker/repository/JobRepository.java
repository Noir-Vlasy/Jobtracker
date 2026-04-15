package com.jobtracker.jobtracker.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("""
    SELECT j FROM Job j
    WHERE j.user = :user
    AND (
        :keyword IS NULL OR :keyword = '' OR
        LOWER(j.company) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
        LOWER(j.role) LIKE LOWER(CONCAT('%', :keyword, '%'))
    )
    AND (
        :status IS NULL OR :status = '' OR j.status = :status
    )
""")
    List<Job> searchJobs(@Param("user") User user,
                         @Param("keyword") String keyword,
                         @Param("status") String status);

}