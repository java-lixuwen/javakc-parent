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
        if (!StringUtils.isEmpty(bookQuery.getBookName()))
            simpleSpecificationBuilder.and("bookName",":",bookQuery.getBookName());
        Page page = bookDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNum - 1, pageSize));
        return page;
        }

}
