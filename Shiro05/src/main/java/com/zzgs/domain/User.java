package com.zzgs.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:22
 * Description:
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private short status;
    private String remark;
}
