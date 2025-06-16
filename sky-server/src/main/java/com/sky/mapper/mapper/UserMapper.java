package com.sky.mapper.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid =#{openid}")
    User getByOpenid(String openid);

    /**
     * 插入用户
     * @param user
     */
    void
    insert(User user);

    User getById(Long userId);

    Integer countByMap(Map<String, Object> map);

    Integer countNewUsers(LocalDateTime begin, LocalDateTime end);
}
