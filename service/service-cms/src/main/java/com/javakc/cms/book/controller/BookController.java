package com.javakc.cms.book.controller;


import com.javakc.cms.book.entity.Book;
import com.javakc.cms.book.service.BookService;
import com.javakc.cms.book.vo.BookQuery;
import com.javakc.commonuitls.api.APICODE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "书籍管理")
@RestController
@RequestMapping("/cms/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation("查询所有书籍的数据")
    @GetMapping
    private APICODE findAll() {

        List<Book> list = bookService.findAll();
        return APICODE.OK().data("items",list);
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation("根据条件进行分页查询-书籍管理")
    @PostMapping("{pageNo}/{pageSize}")
    private APICODE pageBook(@RequestBody(required = false) BookQuery bookQuery , @PathVariable Integer pageNo, @PathVariable Integer pageSize){

        Page<Book> page = bookService.pageBook(bookQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();  //得到数据总数
        List<Book> list = page.getContent();  //得到数据

        APICODE apicode = APICODE.OK().data("total", totalElements).data("items", list);  //返回给前端  数据总数   数据
        return apicode;
    }

    /**
     * 新增
     * @param book
     * @return
     */
    @ApiOperation("新增书籍")
    @PostMapping("saveBook")
    public APICODE saveBook(@RequestBody Book book){
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    /**
     * 删除
     * @param bookId
     * @return
     */
    @ApiOperation("根据出入的Id进行删除")
    @DeleteMapping("{bookId}")
    public APICODE deleteBook(@PathVariable Integer bookId){
        bookService.removeById(bookId);
        return APICODE.OK();
    }

    /**
     * 根据ID 获取（查询到单条数据）
     * @return
     */
    @ApiOperation("根据传入的ID先进行查询 获取到数据 然后修改")
    @GetMapping("{bookId}")
    public APICODE view ( @PathVariable Integer bookId){
        Book book = bookService.getById(bookId);  //根据id先获取到单条数据
        return APICODE.OK().data("book",book);
    }

    /**
     *修改数据
     * @param book
     * @return
     */
    @ApiOperation("修改书籍")
    @PutMapping("updateBook")
    public APICODE updateBook(@RequestBody Book book){
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    @ApiOperation("书籍上下架状态")
    @PutMapping("{bookId}/{zhuangtai}")
    public APICODE shangxiajiaBook(@PathVariable Integer bookId, @PathVariable Integer zhuangtai){
        // 根据id获取单条数据
        Book book= bookService.getById(bookId);
        book.setZhuangtai(zhuangtai); //设置数据状态
        //修改
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

}
