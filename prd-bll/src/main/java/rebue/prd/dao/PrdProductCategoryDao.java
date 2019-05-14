package rebue.prd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rebue.prd.jo.PrdProductCategoryJo;

public interface PrdProductCategoryDao extends JpaRepository<PrdProductCategoryJo, java.lang.Long> {
}
