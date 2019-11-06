package rebue.prd.svc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rebue.prd.dao.PrdProductPicDao;
import rebue.prd.jo.PrdProductPicJo;
import rebue.prd.mapper.PrdProductPicMapper;
import rebue.prd.mo.PrdProductPicMo;
import rebue.prd.svc.PrdProductPicSvc;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;

/**
 * 产品图片
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
public class PrdProductPicSvcImpl
		extends BaseSvcImpl<java.lang.Long, PrdProductPicJo, PrdProductPicDao, PrdProductPicMo, PrdProductPicMapper>
		implements PrdProductPicSvc {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(PrdProductPicSvcImpl.class);

	@Resource
	private PrdProductPicSvc thisSvc;

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int add(PrdProductPicMo mo) {
		_log.info("添加产品图片");
		// 如果id为空那么自动生成分布式id
		if (mo.getId() == null || mo.getId() == 0) {
			mo.setId(_idWorker.getId());
		}
		return super.add(mo);
	}

	/**
	 * 根据产品id删除产品图片
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Ro delByProductId(Long productId) {
		_log.info("根据产品id删除产品图片的请求参数为：{}", productId);
		Ro ro = new Ro();
		if (productId == null) {
			_log.error("根据产品id删除产品图片时发现产品id为空");
			ro.setResult(ResultDic.PARAM_ERROR);
			ro.setMsg("参数不正确");
			return ro;
		}
		
		try {
	        int delResult = _mapper.delectByProductId(productId);
	        _log.info("根据产品id删除产品图片的返回值为：{}", delResult);
        } catch (Exception e) {
            throw new RuntimeException("删除产品图片出现异常");
        }
//      这里注释是因为新添加的商品是没有旧的图片的
//		if (delResult < 1) { 
//			_log.error("根据产品id删除产品图片时出现异常，请求的参数为：{}", productId);
//			ro.setResult(ResultDic.FAIL);
//			ro.setMsg("操作出错");
//			return ro;
//		}

		_log.error("根据产品id删除产品图片成功，请求的参数为：{}", productId);
		ro.setResult(ResultDic.SUCCESS);
		ro.setMsg("操作成功");
		return ro;
	}

	/**
	 * 根据产品id批量添加产品图片
	 * 
	 * @param pics
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Ro batchAddPicByProductId(List<PrdProductPicMo> pics, Long productId) {
		_log.info("批量添加产品图片的请求参数为：{}", String.valueOf(pics));
		Ro ro = new Ro();
		if (pics.size() == 0) {
			_log.error("批量添加产品图片时发现请求参数为pics");
			ro.setResult(ResultDic.PARAM_ERROR);
			ro.setMsg("参数有误");
			return ro;
		}

		for (PrdProductPicMo picMo : pics) {
			picMo.setId(_idWorker.getId());
			picMo.setProductId(productId);
			_log.info("添加产品信息添加产品图片信息的参数为：{}", picMo);
			int addProductPicResult = thisSvc.add(picMo);
			_log.info("添加产品信息添加产品图片信息的返回值为：{}", addProductPicResult);
			if (addProductPicResult != 1) {
				_log.error("添加产品信息添加产品图片出现错误，请求的参数为：{}", picMo);
				throw new RuntimeException("添加图片出现异常");
			}
		}

		_log.info("批量添加产品图片成功，请求的参数为：{}", String.valueOf(pics));
		ro.setResult(ResultDic.SUCCESS);
		ro.setMsg("批量添加成功");
		return ro;
	}
}
