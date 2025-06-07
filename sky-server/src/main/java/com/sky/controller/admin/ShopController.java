package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺的营业状态为：",status==1?"营业中":"已打烊");
        redisTemplate.opsForValue().set("status",status);
        return Result.success();
    }

    /**
     * 获取当前店铺营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取当前店铺营业状态")
    public Result<Integer> getStatus() {
        Integer status = (Integer) redisTemplate.opsForValue().get("status");
        log.info("当前店铺的营业状态为：",status==1?"营业中":"已打烊");
        return Result.success(status);
    }
}
