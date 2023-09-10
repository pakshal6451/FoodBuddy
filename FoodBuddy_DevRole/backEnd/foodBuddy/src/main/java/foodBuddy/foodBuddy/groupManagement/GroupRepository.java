package foodBuddy.foodBuddy.groupManagement;

import foodBuddy.foodBuddy.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<AppGroup,Long> {
    @Query("SELECT groupName FROM AppGroup WHERE groupCode = ?1")
    String findGroupByCode(String groupCode);

    Optional<AppGroup> findByGroupName(String groupName);

}
