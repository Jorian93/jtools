package cn.jorian.common.jtools.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * 
 * description 基础实体类
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:10:50 PM
 *
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -6863631445166144741L;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "create_time")
	LocalDateTime createTime;

	@Column(name = "create_by", length = 50)
	String createBy;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "updated_time")
	LocalDateTime updatedTime;

	@Column(name = "updated_by", length = 50)
	String updatedBy;

	/* 分组校验 */
    public @interface Create {}

    /* 分组校验 */
    public @interface Update {}

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }
}
