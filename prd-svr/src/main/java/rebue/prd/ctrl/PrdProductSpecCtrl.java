package rebue.prd.ctrl;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rebue.prd.mo.PrdProductSpecMo;
import rebue.prd.svc.PrdProductSpecSvc;
import rebue.prd.to.ModifyProductSpecTo;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;

/**
 * 产品规格
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class PrdProductSpecCtrl {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(PrdProductSpecCtrl.class);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Resource
	private PrdProductSpecSvc svc;

	/**
	 * 有唯一约束的字段名称
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String _uniqueFilesName = "某字段内容";

	/**
	 * 添加产品规格
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PostMapping("/prd/productspec")
	IdRo add(@RequestBody PrdProductSpecMo mo) throws Exception {
		_log.info("add PrdProductSpecMo: {}", mo);
		IdRo ro = new IdRo();
		try {
			int result = svc.add(mo);
			if (result == 1) {
				String msg = "添加成功";
				_log.info("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.SUCCESS);
				ro.setId(mo.getId().toString());
				return ro;
			} else {
				String msg = "添加失败";
				_log.error("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.FAIL);
				return ro;
			}
		} catch (DuplicateKeyException e) {
			String msg = "添加失败，" + _uniqueFilesName + "已存在，不允许出现重复";
			_log.error(msg + ": mo-" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		} catch (RuntimeException e) {
			String msg = "添加失败，出现运行时异常";
			_log.error(msg + ": mo-" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 修改产品规格
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PutMapping("/prd/productspec")
	Ro modify(@RequestBody PrdProductSpecMo mo) throws Exception {
		_log.info("modify PrdProductSpecMo: {}", mo);
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
	 * 删除产品规格
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DeleteMapping("/prd/productspec")
	Ro del(@RequestParam("id") java.lang.Long id) {
		_log.info("del PrdProductSpecMo by id: {}", id);
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
	 * 查询产品规格
	 *
	 * @mbg.overrideByMethodName
	 */
	@GetMapping("/prd/productspec")
	List<ModifyProductSpecTo> list(PrdProductSpecMo mo) {
		_log.info("list PrdProductSpecMo:" + mo);
		return svc.listEx(mo);
	}

	/**
	 * 获取单个产品规格
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/prd/productspec/getbyid")
	PrdProductSpecMo getById(@RequestParam("id") java.lang.Long id) {
		_log.info("get PrdProductSpecMo by id: {}", id);
		return svc.getById(id);
	}
}
