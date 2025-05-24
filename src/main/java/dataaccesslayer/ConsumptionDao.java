
package dataaccesslayer;

import java.util.List;
import transferobjects.ConsumptionDTO;

/**
 * DAO interface for accessing consumption records from the database.
 * Provides method to get consumption data along with vehicle rate.
 * 
 * @author Cheng Qian
 */
public interface ConsumptionDao {
    /**
     * Retrieves all consumption records joined with vehicle's consumption rate.
     * @return list of ConsumptionDTO objects
     */
    List<ConsumptionDTO> getAllConsumptionWithRate();
    
    
    /**
    * Inserts a new consumption record into the database.
    *
    * @param consumptionDTO the ConsumptionDTO containing data to insert
    */
    void addRecord(ConsumptionDTO consumptionDTO);
    
    List<ConsumptionDTO> getAllConsumptionWithRateByType(String type);
}
