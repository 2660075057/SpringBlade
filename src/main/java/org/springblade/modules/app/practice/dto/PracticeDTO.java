package org.springblade.modules.app.practice.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.modules.app.practice.entity.Practice;

import java.time.LocalDateTime;

/**
 * 实体类
 *
 * @author tjt
 * @since 2019-12-14
 */
@Data
@TableName("blade_Practice")
@EqualsAndHashCode()
@ApiModel(value = "practice对象", description = "practice对象")
public class PracticeDTO extends Practice {

	private String userName;
}
