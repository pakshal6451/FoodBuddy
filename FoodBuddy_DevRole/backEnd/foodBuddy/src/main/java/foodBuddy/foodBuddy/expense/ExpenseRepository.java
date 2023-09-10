package foodBuddy.foodBuddy.expense;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@Transactional
public interface ExpenseRepository extends JpaRepository<userExpenses,Long> {
    @Query("SELECT emailId from userExpenses WHERE emailId=?1")
    Optional<userExpenses> findUserExpenseExists(String emailId);

    @Query("SELECT amount from userExpenses where emailId =:emailId")
    Double getPastUserExpenses(String emailId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE userExpenses SET amount =:amount WHERE emailId = :emailId ")
    void updateUserExpense(@Param("amount")Double amount, @Param("emailId")String emailId);

}
