package com.jnchen.mkettle.utils.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by caojingchen on 2017/12/28.
 */
@Data
public class BaseModel implements Serializable{
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modified;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date removed;
    private String creater;
    private String modifier;
    private String remover;
}
