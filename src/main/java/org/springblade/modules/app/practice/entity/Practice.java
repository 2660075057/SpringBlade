package org.springblade.modules.app.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

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
public class Practice extends BaseEntity {

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private Integer appearCount;
	private Integer clickCount;
	private String accuracyRate;
	private Integer userId;
	private LocalDateTime createTime;
	private Integer isDeleted;
	private Integer totalTime;
	private Integer exerciseValue;
	private Integer reactionTime;
	@TableField(exist = false)
	private String UserName;
	private String exerciseVersion;
	private String grasp;
	private String judge;
}
