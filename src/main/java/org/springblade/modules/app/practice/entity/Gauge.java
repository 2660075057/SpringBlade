package org.springblade.modules.app.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

@Data
@TableName("blade_gauge")
@EqualsAndHashCode()
@ApiModel(value = "gauge", description = "gauge对象")
public class Gauge extends BaseEntity {
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	private Integer firstTime;
	private Integer secondTime;
	private Integer thirdTime;
	private Integer userId;
	@TableField(exist = false)
	private String userName;
}
