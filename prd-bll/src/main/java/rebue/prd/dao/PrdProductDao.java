package rebue.prd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rebue.prd.jo.PrdProductJo;

public interface PrdProductDao extends JpaRepository<PrdProductJo, java.lang.Long> {
}
