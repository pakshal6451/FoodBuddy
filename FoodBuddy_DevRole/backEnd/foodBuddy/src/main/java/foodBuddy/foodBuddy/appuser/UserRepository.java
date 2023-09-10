package foodBuddy.foodBuddy.appuser;

import foodBuddy.foodBuddy.groupManagement.ViewGroupUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long>  {
    Optional<AppUser> findByEmail(String email);
    
    @Query("SELECT password FROM AppUser WHERE email = ?1")
    String findPasswordByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE AppUser SET groupCode = :groupCode, groupName=:groupName WHERE email = :userName")
    void UpdateGroupName(@Param("groupCode") String groupCode,@Param("groupName")String groupName,@Param("userName") String userName );

    @Query("SELECT new foodBuddy.foodBuddy.groupManagement.ViewGroupUsers(u.email, u.firstName, u.lastName) FROM AppUser u WHERE u.groupCode = :groupCode")
    List<ViewGroupUsers> findUsersByGroupCode(@Param("groupCode") String groupCode);

    @Query("SELECT groupCode FROM AppUser WHERE email = ?1")
    String findGroupByEmail(String email);

    @Query("SELECT u.email FROM AppUser u WHERE u.groupCode = :groupCode")
    List<String> findUsernames(@Param("groupCode") String groupCode);

}
