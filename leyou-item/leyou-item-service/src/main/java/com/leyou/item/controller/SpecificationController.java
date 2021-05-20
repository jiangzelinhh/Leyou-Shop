package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.Impl.SpecificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationServiceImpl specificationService;

    /**
     * 根据分类id查询对应的规格类型
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid")Long cid){

        if(cid==null || cid.longValue()<0){
            // 响应400，相当于ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return  ResponseEntity.badRequest().build();
        }
        List<SpecGroup> groups = specificationService.queryGroupsByCid(cid);
        if(CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(groups);
    }

    /**
     * 根据规格类型id查询对应的规格参数
     * * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid",required = false)Long gid,
            @RequestParam(value = "cid",required = false)Long cid,
            @RequestParam(value = "generic",required = false)Boolean generic,
           @RequestParam(value = "searching",required = false)Boolean searching
    ){


         List<SpecParam> params = specificationService.queryParams(gid,cid,generic,searching);
        if (CollectionUtils.isEmpty(params)){
            return ResponseEntity.notFound().build();
        }
          return ResponseEntity.ok(params);
    }



}
