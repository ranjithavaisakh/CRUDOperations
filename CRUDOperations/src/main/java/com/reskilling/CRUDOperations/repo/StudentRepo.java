package com.reskilling.CRUDOperations.repo;

import com.reskilling.CRUDOperations.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> { //Object on which the CRUD operations are to be performed and the type of the primary key

}
