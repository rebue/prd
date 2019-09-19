package rebue.prd.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.prd.mo.PrdProductMo;
import rebue.sbs.feign.FeignConfig;

@FeignClient(name = "prd-svr", configuration = FeignConfig.class)
public interface PrdProductSvc {

    @GetMapping("/prd/product/getbyid")
    PrdProductMo getById(@RequestParam("id") java.lang.Long id);

}
