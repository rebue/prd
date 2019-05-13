package rebue.prd.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.prd.mo.PrdProductPicMo;
import rebue.robotech.mapper.MybatisBaseMapper;

@Mapper
public interface PrdProductPicMapper extends MybatisBaseMapper<PrdProductPicMo, String> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(String id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(PrdProductPicMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(PrdProductPicMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    PrdProductPicMo selectByPrimaryKey(String id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(PrdProductPicMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(PrdProductPicMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductPicMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<PrdProductPicMo> selectSelective(PrdProductPicMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(String id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(PrdProductPicMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(PrdProductPicMo record);
}