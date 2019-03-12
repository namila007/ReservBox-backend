package me.namila.reservbox.ReservBox.Repository;

import me.namila.reservbox.ReservBox.Model.Contract;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ContractRepository extends CrudRepository<Contract, Integer>
{
    @Query("SELECT distinct c.rooms , c.hotel from  Contract c  inner join c.hotel h inner join c.rooms r where r.maxAdults >= :adults and c.startDate <= :startDate and c.endDate >= :endDate")
    List<Contract> getSearch(@Param("adults") int adults,
                             @Param("startDate") Date startDate,
                             @Param("endDate") Date endDate);
}
