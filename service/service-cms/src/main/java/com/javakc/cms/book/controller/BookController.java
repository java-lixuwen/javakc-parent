package com.javakc.cms.book.controller;


import com.alibaba.excel.EasyExcel;
import com.javakc.cms.book.entity.Book;
import com.javakc.cms.book.listener.ExcelListener;
import com.javakc.cms.book.service.BookService;
import com.javakc.cms.book.vo.BookData;
import com.javakc.cms.book.vo.BookQuery;
import com.javakc.commonuitls.api.APICODE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
        return APICODE.OK().data("items", list);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ApiOperation("根据条件进行分页查询-书籍管理")
    @PostMapping("{pageNo}/{pageSize}")
    private APICODE pageBook(@RequestBody(required = false) BookQuery bookQuery, @PathVariable Integer pageNo, @PathVariable Integer pageSize) {

        Page<Book> page = bookService.pageBook(bookQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();  //得到数据总数
        List<Book> list = page.getContent();  //得到数据

        APICODE apicode = APICODE.OK().data("total", totalElements).data("items", list);  //返回给前端  数据总数   数据
        return apicode;
    }

    /**
     * 新增
     *
     * @param book
     * @return
     */
    @ApiOperation("新增书籍")
    @PostMapping("saveBook")
    public APICODE saveBook(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    /**
     * 删除
     *
     * @param bookId
     * @return
     */
    @ApiOperation("根据出入的Id进行删除")
    @DeleteMapping("{bookId}")
    public APICODE deleteBook(@PathVariable Integer bookId) {
        bookService.removeById(bookId);
        return APICODE.OK();
    }

    /**
     * 根据ID 获取（查询到单条数据）
     *
     * @return
     */
    @ApiOperation("根据传入的ID先进行查询 获取到数据 然后修改")
    @GetMapping("{bookId}")
    public APICODE view(@PathVariable Integer bookId) {
        Book book = bookService.getById(bookId);  //根据id先获取到单条数据
        return APICODE.OK().data("book", book);
    }

    /**
     * 修改数据
     *
     * @param book
     * @return
     */
    @ApiOperation("修改书籍")
    @PutMapping("updateBook")
    public APICODE updateBook(@RequestBody Book book) {
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    @ApiOperation("书籍上下架状态")
    @PutMapping("{bookId}/{zhuangtai}")
    public APICODE shangxiajiaBook(@PathVariable Integer bookId, @PathVariable Integer zhuangtai) {
        // 根据id获取单条数据
        Book book = bookService.getById(bookId);
        book.setZhuangtai(zhuangtai); //设置数据状态
        //修改
        bookService.saveOrUpdate(book);
        return APICODE.OK();
    }

    /**
     * EasyExcel 导出
     * @param response
     */
    @ApiOperation(value = "Excel导出", notes = "使用了阿里巴巴的 EasyExcel技术的导出功能")
    @GetMapping("daochuEasyExcel")
    public void daochuEasyExcel(HttpServletResponse response) {
        //①导出Excel前，要先查询到数据
        List<Book> list = bookService.findAll();
        // ② 创建封装对象的集合
        List<BookData> bookDataList = new ArrayList<>();
        //③ 进行数据的转换，也就是把查询到的Book 数据放到 分装对象的集合中，用set就可以，但是比较复杂，以下是简单方式
        for (Book book : list) {
            BookData bookData = new BookData();   // 数据每次循环之前，都先创建一个对象  里面现在是空的
            //然后将数据放进去，也就是 数据的复制
            BeanUtils.copyProperties(book, bookData);  // 用BeanUtils.copyProperties这个方法进行复制，括号里面 第一个是：原始数据，第二个是：目标数据
            // 将复制好的数据，放到bookDataList 集合中
            bookDataList.add(bookData);
        }
        try {
            //设置导出的文件名字
            response.reset();
            String fileName = "booklist";
            //设置响应格式  固定格式 教材粘贴就可以

            response.setContentType("application/vnd.ms-excel; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8") + ".xlsx");
            // 导出
            EasyExcel.write(response.getOutputStream(), BookData.class).sheet("书籍列表").doWrite(bookDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * EasyExcel 导入
     * @param file
     */
    @ApiOperation(value = "Excel导入", notes = "使用了阿里巴巴的 EasyExcel技术的导入功能")
    @PostMapping("daoruEasyExcel")
    public void daoruEasyExcel(MultipartFile file) {
        try{
           EasyExcel.read(file.getInputStream(),BookData.class,new ExcelListener(bookService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Excel导出
     * @param response
     */
    @ApiOperation(value = "Excel导出", notes = "使用了阿里巴巴的 poi技术的导出功能")
    @GetMapping("daochuExcel")
    public void daochuExcel(HttpServletResponse response){
        bookService.daochuExcel(response);
    }

    /**
     * Excel 导入
     * @param file
     */
    @ApiOperation(value = "Excel导入", notes = "使用了阿里巴巴的 poi技术的导入功能")
    @PostMapping("daoruExcel")
    public void daoruExcel(MultipartFile file){
       bookService.daoruExcel(file);
    }

}

