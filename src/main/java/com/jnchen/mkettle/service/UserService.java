package com.jnchen.mkettle.service;

import com.jnchen.mkettle.domain.User;
import com.jnchen.mkettle.utils.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by caojingchen on 2017/12/28.
 */
@Service
public class UserService {
    private static final String CLASSNAME = UserService.class.getName()+".";

    @Autowired
    private Dao dao;

    public User getUser(String userId){
        return dao.get(CLASSNAME,"getUser",userId);
    }

    public User selectByUsername(String username) {
        return dao.get(CLASSNAME,"selectByUsername",username);
    }
}
