package com.poit.dao;

import com.poit.criteria.Criteria;
import com.poit.repository.entity.User;
import java.util.List;

public interface UserDAO {
    List<User> find(Criteria criteria) throws RuntimeException;
}
