package rebue.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import rebue.prd.mo.PrdProductPicMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductPicMapper extends MybatisBaseMapper<PrdProductPicMo, Long> {
	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	int insert(PrdProductPicMo record);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	int insertSelective(PrdProductPicMo record);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	PrdProductPicMo selectByPrimaryKey(Long id);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	int updateByPrimaryKeySelective(PrdProductPicMo record);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	int updateByPrimaryKey(PrdProductPicMo record);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	List<PrdProductPicMo> selectAll();

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	List<PrdProductPicMo> selectSelective(PrdProductPicMo record);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	boolean existByPrimaryKey(Long id);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	boolean existSelective(PrdProductPicMo record);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	int countSelective(PrdProductPicMo record);

	/**
	 * 根据产品id删除产品图片
	 * 
	 * @param productId 产品id
	 * @return
	 */
	@Delete("delete from PRD_PRODUCT_PIC where PRODUCT_ID = #{productId,jdbcType=BIGINT}")
	int delectByProductId(@Param("productId") Long productId);
}