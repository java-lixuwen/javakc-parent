package com.javakc.cms.book.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.javakc.cms.book.entity.Book;
import com.javakc.cms.book.service.BookService;
import com.javakc.cms.book.vo.BookData;
import org.springframework.beans.BeanUtils;

/**
 * 表格监听器
 * @Progrom: javakc-parent
 * @ClassName: ExcelListener
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/17 10:55
 */
public class ExcelListener extends AnalysisEventListener<BookData> {

    private BookService bookService;

    public ExcelListener(){

    }
    public ExcelListener(BookService bookService){
        this.bookService = bookService;
    }

    @Override
    public void invoke(BookData bookData, AnalysisContext analysisContext) {
        // ① 创建空的实体
        Book book = new Book();
        // ② 复制数据
        BeanUtils.copyProperties(bookData,book);
        //③ 保存数据 ，但是监听器不能调service方法，所以在上方先写两个构造函数，然后进行调用
        bookService.saveOrUpdate(book);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
