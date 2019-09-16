package rebue.prd.so;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rebue.robotech.so.So;

/**
 * 产品规格编码搜索对象
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PrdProductSpecCodeSo extends So implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品规格ID
     */
    private Long productSpecId;

    /**
     * 产品规格编码(目前存放的是条形码)
     */
    private String code;

}