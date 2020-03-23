package rebue.prd.svc;

import java.util.List;

import rebue.prd.jo.PrdProductSpecCodeJo;
import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.prd.ro.BarcodeRo;
import rebue.prd.ro.PrdDetailRo;
import rebue.prd.to.AddonlineByCodeTo;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;

/**
 * 产品规格编码
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface PrdProductSpecCodeSvc extends BaseSvc<java.lang.Long, PrdProductSpecCodeMo, PrdProductSpecCodeJo> {

    BarcodeRo getGoodsDetailByBarcode(String barcode,String shopId);

    /**
     * 根据条码后4位模糊查找
     * 
     * @param code
     * @return
     */
    List<PrdProductSpecCodeMo> selectByCode(String code);

    /**
     * 根据条码查询产品
     * 
     * @param Code
     * @return
     */
    PrdDetailRo getDetailByCode(String Code);

    /**
     * 根据条码上线商品
     * 
     * @param to
     * @return
     */
    Ro addOnlineBycode(AddonlineByCodeTo to);
}
