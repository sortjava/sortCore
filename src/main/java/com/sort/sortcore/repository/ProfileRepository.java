package com.sort.sortcore.repository;

import com.sort.sortcore.data.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
    Profile findByEmail(String emailId);
}