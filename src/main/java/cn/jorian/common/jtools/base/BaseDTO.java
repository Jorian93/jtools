package cn.jorian.common.jtools.base;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * 
 * description 基础DTO
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:08:21 PM
 *
 */
@Data
public class BaseDTO implements Serializable {

    private LocalDateTime createTime;

    private String createBy;

    private LocalDateTime updatedTime;

    private String updatedBy;

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
