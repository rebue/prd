package rebue.prd.svc;

import java.util.List;

import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.so.PrdProductSpecSo;
import rebue.robotech.svc.EsBaseSvc;

/**
 * 产品规格
 */
public interface PrdProductSpecEsSvc extends EsBaseSvc<PrdProductSpecSo> {
    List<PrdProductSpecMo> selectByName(String name);
}
