package com.xiao.gs.view.excel;

import com.xiao.gs.data.jpa.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author luoxiaoxiao
 */
@Slf4j
@Component
public class UserExcelView extends AbstractXlsView {

    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(@NonNull Map<String, Object> model, @NonNull Workbook workbook, @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws Exception {
        //url中传中文必须转成%编码才能识别
        String filename = URLEncoder.encode("用户表格.xls", "utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        //对应一个excel文件
        HSSFWorkbook book = (HSSFWorkbook) workbook;
        //在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = book.createSheet("user sheet");
        //设置表头第0行
        HSSFRow row = sheet.createRow(0);
        String[] colNames = {"编号", "用户名", "密码"};
        for (int i = 0; i < colNames.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(colNames[i]);
        }
        List<User> users = (List<User>) model.get("users");
        //第0行是表头，所以设置从第一行开始
        int rowIndex = 1;
        String[] propNames = {"id", "username", "username"};
        for (User user : users
                ) {
            HSSFRow sheetRow = sheet.createRow(rowIndex);
            for (int i = 0; i < propNames.length; i++) {
                HSSFCell cell = sheetRow.createCell(i);
                //使用反射取值
                cell.setCellValue(BeanUtils.getProperty(user, propNames[i]));
            }
            rowIndex++;
        }
    }

}
