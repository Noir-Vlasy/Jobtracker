package com.jobtracker.jobtracker.repository;

import java.util.List;
import com.jobtracker.jobtracker.model.Job;
import com.jobtracker.jobtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllByUser(User user);

}