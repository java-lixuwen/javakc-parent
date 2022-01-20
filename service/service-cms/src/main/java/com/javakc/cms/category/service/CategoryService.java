package com.javakc.cms.category.service;

import com.javakc.cms.category.dao.CategoryDao;
import com.javakc.cms.category.entity.Category;
import com.javakc.cms.category.vo.ErjiFenlei;
import com.javakc.cms.category.vo.YijiFenlei;
import com.javakc.oss.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import jpa.base.service.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Progrom: javakc-parent
 * @ClassName: CategoryService
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/18 21:48
 */
@Service
public class CategoryService extends BaseService<CategoryDao, Category> {
    /**
     * 获取分类的树形结构数据（查询方法）
     */
    public List<YijiFenlei> getCategoryList() {
        //①查询一级分类，parentId=0说明是一级分类，得到了条件，
        Specification<Category> yijiSpecification = new SimpleSpecificationBuilder<>().and("parentId", "=", "0").getSpecification();
        //②然后调Dao方法调用
        List<Category> yijiCategories = dao.findAll(yijiSpecification);

        //③查询二级分类 parentId!=0说明是二级分类，得到了条件，
        Specification<Category> erjiSpecification = new SimpleSpecificationBuilder<>().and("parentId", "!=", "0").getSpecification();
        //④然后调Dao方法调用
        List<Category> erjiCategories = dao.findAll(erjiSpecification);

        // ⑤创建一级分类集合,以上查出了 一级分类 二级分类的数据，把数据放进yijiFenleiList中
        List<YijiFenlei> yijiFenleiList = new ArrayList<>();
        // ⑥因为一级分类有多条数据，所以先循环出来
        for (int i = 0; i < yijiCategories.size(); i++) {
            // ⑦然后获取出一级分类的数据
            Category category1 = yijiCategories.get(i);
            // ⑧创建一级分类你分装对象
            YijiFenlei yijiFenlei = new YijiFenlei();
            // ⑨把一节分类的数据复制到 一级分类的分装对象中
            BeanUtils.copyProperties(category1, yijiFenlei);

            //⒁创建二级分类分装集合
            List<ErjiFenlei> erjiFenleiList = new ArrayList<>();
            //⑩循环二级分类
            for (int j = 0; j < erjiCategories.size(); j++) {
                //⑾获取二级分类数据
                Category category2 = erjiCategories.get(j);
                //⑿判断当前二级分类中的内容是不是 一级分类中的内容  也就是说 一级分类的id是二级分类的patentId
                if (category1.getId().equals(category2.getParentId())) {
                    // ⒀一级分类id 等于 二级分类parentId 后， 创建 二级分类封装对象
                    ErjiFenlei erjiFenlei = new ErjiFenlei();
                    //⒂把二级分类的数据复制到二级分类封装对象中
                    BeanUtils.copyProperties(category2, erjiFenlei);
                    //⒃将二级分装对象放置到二级分装对象集合中
                    erjiFenleiList.add(erjiFenlei);
                }
            }
            //⒄然后将二级分类分装集合放置到 一级分类分装对象中
            yijiFenlei.setErjiFenleiList(erjiFenleiList);
            //⒅然后将一级分类分装对象放置一级分类分装集合中
            yijiFenleiList.add(yijiFenlei);
        }
        //⒆ 返会一级分类集合
        return yijiFenleiList;
    }
}
