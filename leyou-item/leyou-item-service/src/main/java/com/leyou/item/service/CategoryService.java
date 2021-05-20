package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> queryCategoriesByPid(Long pid);

    //通过品牌id查询商品分类
    List<Category> queryByBrandId(Long bid);

    List<String> queryNamesByIds(List<Long> ids);
}
