package com.s3.individual.s3individual.Persistance;

import com.s3.individual.s3individual.Persistance.Entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PersonRepositoryJPA extends JpaRepository<PersonEntity,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE PersonEntity personEntity SET personEntity.name = :name, personEntity.address = :address, personEntity.email = :email, personEntity.password = :password, personEntity.role = :role  WHERE personEntity.id = :id")
    void updatePerson(@Param("id") Long id, @Param("name") String name, @Param("address") String address, @Param("email") String email, @Param("password") String password, @Param("role") String role);

    PersonEntity findByEmail(String email);
}
