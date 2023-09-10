package foodBuddy.foodBuddy.inventory;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InventoryRepository extends JpaRepository<InventoryEntity,Long> {
    @Query("SELECT itemName FROM InventoryEntity WHERE itemName = ?1 and groupCode = ?2" )
    Optional<InventoryEntity>  findInventoryEntitiesByItemName(String itemName,String groupCode);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE InventoryEntity SET quantity = :quantity, expDate = :expDate, amount=:amount WHERE itemName = :itemName and groupCode = :groupCode")
    void updateItemDetails(@Param("itemName") String itemName ,@Param("groupCode") String groupCode, @Param("expDate") String expDate, @Param("quantity") int quantity,@Param("amount")Double amount);

    @Query("SELECT new foodBuddy.foodBuddy.inventory.ViewItems(inventory.itemName, inventory.expDate, inventory.quantity,inventory.amount) FROM InventoryEntity inventory WHERE inventory.groupCode = :groupCode")
    List<ViewItems> findItemList(@Param("groupCode") String groupCode);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM InventoryEntity WHERE itemName = :itemName AND groupCode = :groupCode")
    void deleteItemfromDB(@Param("itemName") String itemName, @Param("groupCode") String groupCode);

    @Query("SELECT inventory.itemName FROM InventoryEntity inventory WHERE inventory.groupCode = :groupCode")
    List<String> findItemNameList(@Param("groupCode") String groupCode);

}
