package rebue.prd.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.prd.mo.PrdProductSpecMo;
import rebue.sbs.feign.FeignConfig;

@FeignClient(name = "prd-svr", configuration = FeignConfig.class, contextId = "prd-svr-productspec")

public interface PrdProductSpecSvc {

    /**
     * 获取单个产品规格
     *
     */
    @GetMapping("/prd/productspec/getbyid")
    PrdProductSpecMo getById(@RequestParam("id") java.lang.Long id);

}
