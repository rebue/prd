package rebue.prd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rebue.prd.jo.PrdCategoryJo;

public interface PrdCategoryDao extends JpaRepository<PrdCategoryJo, java.lang.Long> {
}
