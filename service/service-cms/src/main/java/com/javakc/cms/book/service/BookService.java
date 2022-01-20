package com.javakc.cms.book.service;

import com.javakc.cms.book.dao.BookDao;
import com.javakc.cms.book.entity.Book;
import com.javakc.cms.book.vo.BookQuery;

import com.javakc.oss.commonutils.jpa.base.service.BaseService;
import com.javakc.oss.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookService extends BaseService<BookDao, Book> {

    @Autowired
    private BookDao bookDao;

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<Book> findAll() {
        List<Book> list = bookDao.findAll();
        return list;
    }

    /**
     * 带条件分页查询
     *
     * @return
     */
    public Page<Book> pageBook(BookQuery bookQuery, Integer pageNo, Integer pageSize) {
        /**
         * 可用操作符
         * = 等值 != 不等值 (字符串  数字)
         * >=  <=  <  (数字)
         * ge大于等于   le小于等于   gt大于   lt小于   eq相等   ne neq 不相等   not非    mod求模 (字符串)
         * : 表示like  %v%
         * l:  表示 v%
         * :l  表示 %v
         * null  表示 is null
         * !null  表示 is not null
         */
        SimpleSpecificationBuilder<Book> simpleSpecificationBuilder = new SimpleSpecificationBuilder<>();
        //根据书籍id查询
        if (!StringUtils.isEmpty(bookQuery.getId())) {
            simpleSpecificationBuilder.and("id", "=", bookQuery.getId());
        }
        //根据书名查询
        if (!StringUtils.isEmpty(bookQuery.getBookName())) {
            simpleSpecificationBuilder.and("bookName", ":", bookQuery.getBookName());
        }
        //根据作者查询
        if (!StringUtils.isEmpty(bookQuery.getAuthor())) {
            simpleSpecificationBuilder.and("author", ":", bookQuery.getAuthor());
        }
        //根据是否连载查询
        if (!StringUtils.isEmpty(bookQuery.getLianzai())) {
            simpleSpecificationBuilder.and("lianzai", ":", bookQuery.getLianzai());
        }
        //根据状态（是否上线）查询
        if (!StringUtils.isEmpty(bookQuery.getZhuangtai())) {
            simpleSpecificationBuilder.and("zhuangtai", ":", bookQuery.getZhuangtai());
        }
        //根据原创（是否原创）查询
        if (!StringUtils.isEmpty(bookQuery.getYuanchuang())) {
            simpleSpecificationBuilder.and("yuanchuang", ":", bookQuery.getYuanchuang());
        }
        //根据一级分类（是否是一级分类）查询
        if (!StringUtils.isEmpty(bookQuery.getYijiFenlei())) {
            simpleSpecificationBuilder.and("yijiFenlei", ":", bookQuery.getYijiFenlei());
        }
        //根据二级分类（是否是二级分类）查询
        if (!StringUtils.isEmpty(bookQuery.getErjiFenlei())) {
            simpleSpecificationBuilder.and("erjiFenlei", ":", bookQuery.getErjiFenlei());
        }
        //根据收费分类（是否收费）查询
        if (!StringUtils.isEmpty(bookQuery.getShoufei())) {
            simpleSpecificationBuilder.and("shoufei", ":", bookQuery.getShoufei());
        }
        //根据授权开始时间查询
        if (!StringUtils.isEmpty(bookQuery.getShouquankaishiDate())) {
            simpleSpecificationBuilder.and("shouquankaishiTime", "ge", bookQuery.getShouquankaishiDate());
        }
        //根据授权结束时间查询
        if (!StringUtils.isEmpty(bookQuery.getShouquanjieshuDate())) {
            simpleSpecificationBuilder.and("shouquankaishiTime", "lt", bookQuery.getShouquanjieshuDate());
        }

        Page page = bookDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo - 1, pageSize));
        return page;
    }

    /**
     * poi方式 导出Excel
     * @param response
     */
    public void daochuExcel(HttpServletResponse response) {
        try {
            // ① 手动设置表头   因为表头有好几个字段，所以是数组集合形式
            String[] biaotou = {"书名", "作者", "一级分类", "二级分类", "是否连载", "字数", "是否上线或未上线", "是否收费", "授权开始时间", "授权结束时间", "是否原创"};
            //② 创建工作簿    也就是创建一个Excel
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
            // ③ 在工作簿中创建sheet  （可以起名字  也可以不起名字）
            HSSFSheet sheet = hssfWorkbook.createSheet();
            // ④ 在当前的sheet中 创建行    （第一行是 表头）   第一行 是 0
            HSSFRow row = sheet.createRow(0);
            // ⑤ 第一行里面的内容是 表头，  设置表头的数据   因为每一条数据 都需要表头 所以用for循环
            for (int i = 0; i < biaotou.length; i++) {
                //Cell 是单元格的意思，   在当前行上创建单元格
                row.createCell(i).setCellValue(biaotou[i]);
            }


            // ⑥查询到所有的数据，并且设置到其他行中
            List<Book> list = bookDao.findAll();

            //⑦ 判断是不是空表
            if (null != list) {
                // (12) 创建授权时间的格式
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //⑧不是空表，进行循环数据
                for (int i = 0; i < list.size(); i++) {
                    //(11) 数据是从list中得到的
                    Book book = list.get(i);
                    //⑨ 创建出其他行
                    HSSFRow hssfRow = sheet.createRow(i + 1);
                    //⑩ 为创建出来的其他行创建单元格，并且设置数据, 设置书名 作者名 等等    里面的数据是从list中得到的，以上 ⑪
                    hssfRow.createCell(0).setCellValue(book.getId());
                    hssfRow.createCell(1).setCellValue(book.getBookName());
                    hssfRow.createCell(2).setCellValue(book.getAuthor());
                    hssfRow.createCell(3).setCellValue(book.getYijiFenlei());
                    hssfRow.createCell(4).setCellValue(book.getErjiFenlei());
                    hssfRow.createCell(5).setCellValue(book.getLianzai());
                    hssfRow.createCell(6).setCellValue(book.getWordNumber());
                    hssfRow.createCell(7).setCellValue(book.getZhuangtai());
                    hssfRow.createCell(8).setCellValue(book.getShoufei());
                    hssfRow.createCell(9).setCellValue(sdf.format(book.getShouquankaishiTime())); //格式化一下授权开始时间 (12)
                    hssfRow.createCell(10).setCellValue(sdf.format(book.getShouquanjieshuTime())); //格式化一下授权结束时间 (12)
                    hssfRow.createCell(11).setCellValue(book.getYuanchuang());
                }
            }
            //(15) 将Excel文件内容输出到客户端浏览器中   文件名是 日期格式
            String fileName = new String(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
            //(13)通过文件流的方式 写入数据
            ServletOutputStream outputStream = response.getOutputStream();
            hssfWorkbook.write(outputStream);
            //(14)刷新流，关闭流
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过poi 导入Excel
     * @param file
     */
    @Transactional
    public void daoruExcel(MultipartFile file) {
        try {

            // ① 创建文件流
            InputStream inputStream = file.getInputStream();
            //② 创建一个工作簿的接口
            Workbook workbook = null;
            //③判断Excel 的格式
            if (file.getOriginalFilename().endsWith(".xlsx")) {
                //④处理xlxs 格式
                workbook = new HSSFWorkbook(inputStream);
            } else {  // ⑤ 处理xls 格式
                workbook = new HSSFWorkbook(inputStream);
            }
            // ⑥ 获取工作簿中的所有sheet 总数
            int numberOfSheets = workbook.getNumberOfSheets();
            //⑦ 循环sheet
            for (int i = 0; i < numberOfSheets; i++) {
                //在当当前工作簿中 逐个获取sheet
                Sheet sheet = workbook.getSheetAt(i);
                //⑧ 获取当前sheet中总行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

                //⑩ 创建实体集合
                List<Book> list = new ArrayList<>();
                // (13) 创建授权时间的格式
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //⑨循环每一行得到数据
                for (int j = 1; j < physicalNumberOfRows; j++) {   //因为第一行是表头不是数据，所以不用循环，所以写 1
                    //(11)创建实体对象
                    Book book = new Book();
                    //(12)获取行
                    Row row = sheet.getRow(j);
                    // 将书名，放到第一列
                    book.setBookName(row.getCell(1).getStringCellValue());
                    book.setAuthor(row.getCell(3).getStringCellValue());
                    book.setYijiFenlei(row.getCell(3).getStringCellValue());
                    book.setErjiFenlei(row.getCell(4).getStringCellValue());
                    book.setLianzai((int) row.getCell(5).getNumericCellValue());
                    book.setWordNumber((int) row.getCell(6).getNumericCellValue());
                    book.setZhuangtai((int) row.getCell(7).getNumericCellValue());
                    book.setShoufei((int) row.getCell(8).getNumericCellValue());
                    book.setShouquankaishiTime(sdf.parse(row.getCell(9).getStringCellValue()));
                    book.setShouquanjieshuTime(sdf.parse(row.getCell(10).getStringCellValue()));
                    book.setYuanchuang((int) row.getCell(11).getNumericCellValue());
                    //(14) 每次循环都把数据放到集合中
                    list.add(book);
                }
                //(15)  批量保存
                dao.saveAll(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    }

