package rebue.prd.svr.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.sbs.feign.FeignConfig;

@FeignClient(name = "prd-svr", configuration = FeignConfig.class, contextId = "prd-svr-product-spec-code")
public interface PrdProductSpecCodeSvc {

    @GetMapping("/prd/productspeccode/select-by-code")
    public List<PrdProductSpecCodeMo> selectByCode(@RequestParam("code") String code);

}
