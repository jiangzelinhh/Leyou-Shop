package com.leyou.item.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;


@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        //初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        // 根据name模糊查询，或者根据首字母查询
        if(StringUtil.isNotEmpty(key)){
           criteria.andLike("name","%"+key+"%").orEqualTo("letter",key);
        }

        //添加分页条件
        PageHelper.startPage(page,rows);

        //添加排序条件
        if(StringUtil.isNotEmpty(sortBy)){
           example.setOrderByClause(sortBy+" "+(desc ? "desc":"asc"));
        }

        List<Brand> brands = brandMapper.selectByExample(example);

        //包装成pageinfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);

        // 包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     */
    public void saveBrand(Brand brand, List<Long> cids) {

        //先新增brand
         brandMapper.insertSelective(brand);
         for(Long cid:cids){
              brandMapper.insertCategoryAndBrand(cid,brand.getId());
         }
    }

    /**
     * 修改品牌
     * @param brand
     * @param cids
     */
    @Override
    public void updateBrand(Brand brand, List<Long> cids) {
        brandMapper.updateByPrimaryKey(brand);
        brandMapper.deleteCategoryAndBrand(brand.getId());
        for(Long cid:cids){
                brandMapper.insertCategoryAndBrand(cid,brand.getId());

        }
    }

    /**
     * 删除品牌
     * @param id
     */
    public boolean deleteBrandByid(Long id) {
        boolean flag = false;
        Brand brand =new Brand();
        brand.setId(id);
        int count1 = brandMapper.deleteCategoryAndBrand(id);
        int count2 = brandMapper.delete(brand);
        if(count1>0&&count2>0){
             flag = true;
        }
        return flag;
    }

    /**
     * 根据分类id查询旗下的品牌
     * @param cid
     * @return
     */
    public List<Brand> queryBrandsByCid(Long cid) {

        return brandMapper.selectBrandByCid(cid);
    }

    @Override
    public Brand queryBrandById(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }
}
