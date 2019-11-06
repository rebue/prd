package rebue.prd.ro;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;


@Data
@JsonInclude(Include.NON_NULL)
public class GetProductRo {
    
    /**
     * 产品名称
     */
    private String  productName;
    
    /**
     * 产品详情
     */
    private String productDetail;

}
