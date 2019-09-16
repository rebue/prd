package rebue.prd.so;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rebue.robotech.so.So;

/**
 * 产品规格属性搜索对象
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PrdProductSpecAttrSo extends So implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品规格ID
     */
    private Long productSpecId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;

}