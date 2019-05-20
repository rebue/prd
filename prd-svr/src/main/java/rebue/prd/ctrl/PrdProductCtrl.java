package rebue.prd.ctrl;

import com.github.pagehelper.PageInfo;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rebue.prd.mo.PrdProductMo;
import rebue.prd.ro.PrdProductListRo;
import rebue.prd.svc.PrdProductSvc;
import rebue.prd.to.AddProductTo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.wheel.turing.JwtUtils;

/**
 * 产品
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class PrdProductCtrl {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(PrdProductCtrl.class);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Resource
	private PrdProductSvc svc;

	@Value("${debug:false}")
	private Boolean isDebug;

	/**
	 * 有唯一约束的字段名称
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String _uniqueFilesName = "某字段内容";

	/**
	 * 修改产品
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PutMapping("/prd/product")
	Ro modify(@RequestBody PrdProductMo mo) throws Exception {
		_log.info("modify PrdProductMo: {}", mo);
		Ro ro = new Ro();
		try {
			if (svc.modify(mo) == 1) {
				String msg = "修改成功";
				_log.info("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.SUCCESS);
				return ro;
			} else {
				String msg = "修改失败";
				_log.error("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.FAIL);
				return ro;
			}
		} catch (DuplicateKeyException e) {
			String msg = "修改失败，" + _uniqueFilesName + "已存在，不允许出现重复";
			_log.error(msg + ": mo=" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		} catch (RuntimeException e) {
			String msg = "修改失败，出现运行时异常";
			_log.error(msg + ": mo-" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 删除产品
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DeleteMapping("/prd/product")
	Ro del(@RequestParam("id") java.lang.Long id) {
		_log.info("del PrdProductMo by id: {}", id);
		int result = svc.del(id);
		Ro ro = new Ro();
		if (result == 1) {
			String msg = "删除成功";
			_log.info("{}: id-{}", msg, id);
			ro.setMsg(msg);
			ro.setResult(ResultDic.SUCCESS);
			return ro;
		} else {
			String msg = "删除失败，找不到该记录";
			_log.error("{}: id-{}", msg, id);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 查询产品
	 *
	 * @mbg.overrideByMethodName
	 */
	@GetMapping("/prd/product")
	PageInfo<PrdProductListRo> list(PrdProductMo mo, @RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 5;
		_log.info("list PrdProductMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<PrdProductListRo> result = svc.pageList(mo, pageNum, pageSize, "CREATE_TIME DESC");
		_log.info("result: " + result);
		return result;
	}

	/**
	 * 获取单个产品
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/prd/product/getbyid")
	PrdProductMo getById(@RequestParam("id") java.lang.Long id) {
		_log.info("get PrdProductMo by id: {}", id);
		return svc.getById(id);
	}

	/**
	 * 添加产品信息
	 * 
	 * @param to
	 * @return
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	@PostMapping("/prd/product")
	Ro addProduct(@RequestBody AddProductTo to, HttpServletRequest req) throws NumberFormatException, ParseException {
		_log.info("添加产品信息的请求参数为：{}", to);
		// 获取当前登录用户id
		Long currentUserId = 520469568947224576L;
		if (!isDebug) {
			currentUserId = JwtUtils.getJwtUserIdInCookie(req);
		}
		final Ro ro = new Ro();
		if (currentUserId == null) {
			ro.setResult(ResultDic.FAIL);
			ro.setMsg("您未登录，请登录后再试。。。");
			return ro;
		}
		to.setOpId(currentUserId);
		try {
			return svc.addProduct(to);
		} catch (Exception e) {
			_log.error("添加产品信息出现异常，{}", e);
			ro.setResult(ResultDic.FAIL);
			ro.setMsg(e.getMessage());
			return ro;
		}
	}
}
