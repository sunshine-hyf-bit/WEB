package org.example.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userName; // 对应 user_name

    private String password;

    private LocalDateTime createTime; // 对应 create_time

    private LocalDateTime updateTime; // 对应 update_time
}

