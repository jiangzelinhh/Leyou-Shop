package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.Impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

      @Autowired
      private CategoryServiceImpl categoryService;

    /**
     * 根据父id查询子节点
     * @param pid
     * @return
     */
     @GetMapping("list")
      public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam("pid") Long pid){

          try {
              if(pid==null || pid.longValue()<0){
                  // 响应400，相当于ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                     return  ResponseEntity.badRequest().build();
              }
              List<Category> categories = categoryService.queryCategoriesByPid(pid);
              if(CollectionUtils.isEmpty(categories)){
                  //响应404
                    return  ResponseEntity.notFound().build();
              }
              return  ResponseEntity.ok(categories);
          } catch (Exception e) {
              e.printStackTrace();
          }
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }

      /*
      通过品牌id查询商品分类
       */
      @GetMapping("bid/{bid}")
      public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){

          List<Category> list =categoryService.queryByBrandId(bid);
          if(CollectionUtils.isEmpty(list)) {
              return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
          return  ResponseEntity.ok(list);
      }

    /**
     * 根据分类id查询商品分类名称
     * @param ids
     * @return
     */
      @GetMapping("names")
      public  ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids")List<Long> ids){

          List<String> names = this.categoryService.queryNamesByIds(ids);
          if (CollectionUtils.isEmpty(names)) {
              return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok(names);
      }

}
