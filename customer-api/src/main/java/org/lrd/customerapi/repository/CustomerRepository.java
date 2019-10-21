package org.lrd.customerapi.repository;

import org.lrd.customerapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.firstName, c.lastName FROM Customer c WHERE c.firstName=:firstName AND c.lastName=:lastName")
    public String fetchCustomer(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.firstName=:firstName AND c.lastName=:lastName")
    public void deleteCustomer(@Param("firstName") String firstName, @Param("lastName") String lastName);

}
