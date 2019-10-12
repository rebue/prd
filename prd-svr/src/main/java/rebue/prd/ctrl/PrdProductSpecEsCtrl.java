package rebue.prd.ctrl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.so.PrdProductSpecSo;
import rebue.prd.svc.PrdProductSpecEsSvc;

/**
 * 产品规格
 */
@RestController
public class PrdProductSpecEsCtrl {
    private static final Logger _log = LoggerFactory.getLogger(PrdProductSpecEsCtrl.class);

    @Resource
    private PrdProductSpecEsSvc svc;

    /**
     * 获取单个产品规格
     */
    @GetMapping("/prd/prdproductspecso/get-by-id")
    PrdProductSpecSo getById(@RequestParam final String id) {
        _log.info("PrdProductSpecEsCtrl.getById:{}", id);
        return svc.getById(id);
    }

    /**
     * 根据产品名称获取产品规格
     */
    @GetMapping("/prd/prdproductspecso/select-by-name")
    List<PrdProductSpecMo> selectByName(@RequestParam final String name) {
        _log.info("PrdProductSpecEsCtrl.getById:{}", name);
        return svc.selectByName(name);
    }
}
