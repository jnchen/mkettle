package com.jnchen.mkettle.domain;

import com.jnchen.mkettle.utils.domain.BaseModel;
import lombok.Data;

/**
 * Created by caojingchen on 2017/12/28.
 */
@Data
public class User extends BaseModel{
    private String name;
    private String pass;
    private int isAdmin;
    private int enable;
}
