package com.javakc.cms.book.service;
import com.javakc.cms.book.dao.BookDao;
import com.javakc.cms.book.entity.Book;
import com.javakc.cms.book.vo.BookQuery;

import com.javakc.oss.commonutils.jpa.base.service.BaseService;
import com.javakc.oss.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookService extends BaseService<BookDao, Book> {

    @Autowired
    private BookDao bookDao;

    /**
     * 查询所有数据
     * @return
     */
    public List<Book> findAll() {
        List<Book> list = bookDao.findAll();
        return list;
    }

    /**
     * 带条件分页查询
     * @return
     */
    public Page<Book> pageBook(BookQuery bookQuery,Integer pageNum,Integer pageSize){
        /**
         * 可用操作符
         * = 等值 != 不等值 (字符串  数字)
         * >=  <=  <  (数字)
         * : 表示like  %v%
         * l:  表示 v%
         * :l  表示 %v
         * null  表示 is null
         * !null  表示 is not null
         */
        SimpleSpecificationBuilder<Book> simpleSpecificationBuilder = new SimpleSpecificationBuilder<>();
        //根据书籍id查询
//        if (!StringUtils.isEmpty(bookQuery.getId()))
//            simpleSpecificationBuilder.and("id",":",bookQuery.getId());
        //根据书名查询
        if (!StringUtils.isEmpty(bookQuery.getBookName()))
            simpleSpecificationBuilder.and("bookName",":",bookQuery.getBookName());
        //根据作者查询
        if (!StringUtils.isEmpty(bookQuery.getAuthor()))
            simpleSpecificationBuilder.and("author",":",bookQuery.getAuthor());
        //根据是否连载查询
        if (!StringUtils.isEmpty(bookQuery.getLianzai()))
            simpleSpecificationBuilder.and("lianzai","ge",bookQuery.getLianzai());
        //根据状态（是否上线）查询
        if (!StringUtils.isEmpty(bookQuery.getZhuangtai()))
            simpleSpecificationBuilder.and("zhuangtai","=",bookQuery.getZhuangtai());
        //根据原创（是否原创）查询
        if (!StringUtils.isEmpty(bookQuery.getYuanchuang()))
            simpleSpecificationBuilder.and("yuanchuang",":",bookQuery.getYuanchuang());
        //根据一级分类（是否是一级分类）查询
        if (!StringUtils.isEmpty(bookQuery.getYijiFenlei()))
            simpleSpecificationBuilder.and("yijiFenlei",":",bookQuery.getYijiFenlei());
        //根据二级分类（是否是二级分类）查询
        if (!StringUtils.isEmpty(bookQuery.getErjiFenlei()))
            simpleSpecificationBuilder.and("erjiFenlei",":",bookQuery.getErjiFenlei());
        //根据收费分类（是否收费）查询
        if (!StringUtils.isEmpty(bookQuery.getShoufei()))
            simpleSpecificationBuilder.and("shoufei",":",bookQuery.getShoufei());
        //根据授权开始时间查询
        if (!StringUtils.isEmpty(bookQuery.getShouquankaishiDate()))
           simpleSpecificationBuilder.and("shouquankaishiTime","ge",bookQuery.getShouquankaishiDate());
        //根据授权结束时间查询
        if (StringUtils.isEmpty(bookQuery.getShouquanjieshuDate()))
            simpleSpecificationBuilder.and("shouquankaishiTime","lt",bookQuery.getShouquanjieshuDate());

        Page page = bookDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNum - 1, pageSize));
        return page;
        }

}
