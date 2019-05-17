package rebue.prd.svc.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import rebue.ise.ro.SaveFileRo;
import rebue.ise.svr.feign.IserSvc;
import rebue.ise.to.SaveFileTo;
import rebue.prd.dao.PrdProductDao;
import rebue.prd.jo.PrdProductJo;
import rebue.prd.mapper.PrdProductMapper;
import rebue.prd.mo.PrdProductMo;
import rebue.prd.mo.PrdProductPicMo;
import rebue.prd.mo.PrdProductSpecCodeMo;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.svc.PrdProductPicSvc;
import rebue.prd.svc.PrdProductSpecCodeSvc;
import rebue.prd.svc.PrdProductSpecSvc;
import rebue.prd.svc.PrdProductSvc;
import rebue.prd.to.AddProductSpecTo;
import rebue.prd.to.AddProductTo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 产品
 *
 * 在单独使用不带任何参数的 @Transactional 注释时， propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED， 而且事务不会针对受控异常（checked exception）回滚。
 *
 * 注意： 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class PrdProductSvcImpl
		extends BaseSvcImpl<java.lang.Long, PrdProductJo, PrdProductDao, PrdProductMo, PrdProductMapper>
		implements PrdProductSvc {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(PrdProductSvcImpl.class);

	@Resource
	private PrdProductSvc thisSvc;

	@Resource
	private PrdProductSpecSvc prdProductSpecSvc;

	@Resource
	private PrdProductSpecCodeSvc prdProductSpecCodeSvc;

	@Resource
	private PrdProductPicSvc prdProductPicSvc;

	@Resource
	private IserSvc iseSvc;

	@Resource
	private Mapper dozerMapper;

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int add(PrdProductMo mo) {
		_log.info("添加产品");
		// 如果id为空那么自动生成分布式id
		if (mo.getId() == null || mo.getId() == 0) {
			mo.setId(_idWorker.getId());
		}
		return super.add(mo);
	}

	/**
	 * 添加产品信息
	 * 
	 * @param to
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Ro addProduct(AddProductTo to) {
		_log.info("添加产品信息的请求参数为：{}", to);
		Ro ro = new Ro();
		if (to.getCategoryId() == null || to.getProductName() == null || to.getPics().size() == 0
				|| to.getSpec().size() == 0) {
			_log.error("添加产品信息时出现参数为null,请求的参数为：{}", to);
			ro.setResult(ResultDic.PARAM_ERROR);
			ro.setMsg("参数有误");
			return ro;
		}

		// 产品ID
		Long productId = _idWorker.getId();

		SaveFileTo saveFileTo = new SaveFileTo();
		saveFileTo.setContent(to.getProductDetail());
		saveFileTo.setFileName(String.valueOf(productId));
		saveFileTo.setFileType("html");
		_log.info("添加产品信息保存产品详情文件的参数为：{}", saveFileTo);
		SaveFileRo saveFileRo = iseSvc.saveFile(saveFileTo);
		_log.info("添加产品信息保存产品详情文件的返回值为：{}", saveFileRo);
		if (saveFileRo.getResult() != 1) {
			_log.error("添加产品信息保存产品详情文件时出现异常，请求的参数为：{}", saveFileTo);
			ro.setResult(ResultDic.FAIL);
			ro.setMsg("保存产品详情出现异常");
			return ro;
		}

		PrdProductMo productMo = new PrdProductMo();
		productMo.setId(productId);
		productMo.setCategoryId(to.getCategoryId());
		productMo.setProductName(to.getProductName());
		productMo.setIsEnabled(true);
		productMo.setManufacturer(to.getManufacturer());
		productMo.setBrand(to.getBrand());
		productMo.setProductDetailPath(saveFileRo.getFilePath());
		productMo.setOpId(to.getOpId());
		productMo.setCreateTime(new Date());
		_log.info("添加产品信息的参数为：{}", productMo);
		int addProductResult = this.add(productMo);
		_log.info("添加产品信息的返回值为：{}", addProductResult);
		if (addProductResult != 1) {
			_log.error("添加产品信息时出现错误，请求的参数为：{}", productMo);
			ro.setResult(ResultDic.FAIL);
			ro.setMsg("添加产品出现异常");
			return ro;
		}

		for (AddProductSpecTo addProductSpecTo : to.getSpec()) {
			// 产品规格ID
			Long productSpecId = _idWorker.getId();
			PrdProductSpecMo productSpecMo = new PrdProductSpecMo();
			productSpecMo.setId(productSpecId);
			productSpecMo.setProductId(productId);
			productSpecMo.setName(addProductSpecTo.getProductSpecName());
			productSpecMo.setUnit(addProductSpecTo.getUnit());
			productSpecMo.setMarketPrice(addProductSpecTo.getMarketPrice());
			productSpecMo.setPicPath(addProductSpecTo.getProductSpecPicPath());
			_log.info("添加产品信息添加产品规格信息的参数为：{}", productSpecMo);
			int addProductSpecResult = prdProductSpecSvc.add(productSpecMo);
			_log.info("添加产品信息添加产品规格信息的返回值为：{}", addProductSpecResult);
			if (addProductSpecResult != 1) {
				_log.error("添加产品信息添加产品规格信息出现错误，请求的参数为：{}", productSpecMo);
				throw new RuntimeException("添加规格信息出现异常");
			}

			PrdProductSpecCodeMo productSpecCodeMo = new PrdProductSpecCodeMo();
			productSpecCodeMo.setId(_idWorker.getId());
			productSpecCodeMo.setProductSpecId(productSpecId);
			productSpecCodeMo.setCode(addProductSpecTo.getProductSpecCode());
			_log.info("添加产品信息添加产品规格编码信息的参数为：{}", productSpecCodeMo);
			int addProductSpecCodeResult = prdProductSpecCodeSvc.add(productSpecCodeMo);
			_log.info("添加产品信息添加产品规格编码信息的返回值为：{}", addProductSpecCodeResult);
			if (addProductSpecCodeResult != 1) {
				_log.error("添加产品信息添加产品规格编码出现错误，请求的参数为：{}", productSpecCodeMo);
				throw new RuntimeException("添加规格编码出现异常");
			}
		}

		for (PrdProductPicMo picMo : to.getPics()) {
			PrdProductPicMo productPicMo = dozerMapper.map(picMo, PrdProductPicMo.class);
			productPicMo.setId(_idWorker.getId());
			productPicMo.setProductId(productId);
			_log.info("添加产品信息添加产品图片信息的参数为：{}", productPicMo);
			int addProductPicResult = prdProductPicSvc.add(productPicMo);
			_log.info("添加产品信息添加产品图片信息的返回值为：{}", addProductPicResult);
			if (addProductPicResult != 1) {
				_log.error("添加产品信息添加产品图片出现错误，请求的参数为：{}", productPicMo);
				throw new RuntimeException("添加图片出现异常");
			}
		}

		_log.info("添加产品信息成功，请求的参数为：{}", to);
		ro.setResult(ResultDic.SUCCESS);
		ro.setMsg("添加成功");
		return ro;
	}
}
