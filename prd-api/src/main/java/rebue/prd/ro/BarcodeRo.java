package rebue.prd.ro;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 
 * @author xym
 * 
 *         扫码后返回的Ro
 */
@Data
@JsonInclude(Include.NON_NULL)
public class BarcodeRo {

    private String barcode;

    private byte result;

    private String msg;

    private List<ProductDetailRo> productDetailList;

    private List<PrdOnlineDetailRo> onlineDetailList;
}
