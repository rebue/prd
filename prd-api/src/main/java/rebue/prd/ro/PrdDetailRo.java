package rebue.prd.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.prd.mo.PrdProductSpecMo;

@Data
@JsonInclude(Include.NON_NULL)
public class PrdDetailRo {

    private byte result;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 产品规格详情
     */
    private PrdProductSpecMo prdProductSpecMo;
}
