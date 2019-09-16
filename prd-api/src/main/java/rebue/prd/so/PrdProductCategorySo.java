package rebue.prd.so;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rebue.robotech.so.So;

/**
 * 产品分类搜索对象
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class PrdProductCategorySo extends So implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类编码
     */
    private String code;

    /**
     * 是否启用
     */
    private Boolean isEnabled;

    /**
     * 操作人ID
     */
    private Long opId;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 以-隔开
     */
    private String fullName;

}
