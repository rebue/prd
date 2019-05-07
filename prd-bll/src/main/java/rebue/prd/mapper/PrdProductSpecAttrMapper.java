package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdProductSpecAttrMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductSpecAttrMapper extends MybatisBaseMapper<PrdProductSpecAttrMo, Long> {
    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdProductSpecAttrMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdProductSpecAttrMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdProductSpecAttrMo selectByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdProductSpecAttrMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdProductSpecAttrMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductSpecAttrMo> selectAll();

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductSpecAttrMo> selectSelective(PrdProductSpecAttrMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdProductSpecAttrMo record);

    /**
    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductSpecAttrMo record);
}