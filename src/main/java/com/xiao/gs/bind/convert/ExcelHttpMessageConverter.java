package com.xiao.gs.bind.convert;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xiao.gs.data.jpa.domain.User;
import com.xiao.gs.model.JsonResult;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;

/**
 * @author luoxiaoxiao
 */
public class ExcelHttpMessageConverter extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {

    public static final MediaType APPLICATION_EXCEL = MediaType.valueOf("application/vnd.ms-excel");

    public ExcelHttpMessageConverter() {
        super(APPLICATION_EXCEL);
    }

    @Override
    protected boolean supports(@NonNull Class<?> clazz) {
        return true;
    }

    @Nullable
    @Override
    protected Object readInternal(@NonNull Class<?> clazz, @NonNull HttpInputMessage inputMessage) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void writeInternal(@NonNull Object o, @NonNull HttpOutputMessage outputMessage) throws IOException {
        HttpHeaders headers = outputMessage.getHeaders();
        // TODO 文件名参数化
        String filename = URLEncoder.encode("用户表格.xls", "utf-8");
        headers.setContentDispositionFormData("attachment", filename);
        Collection data = getActualData((JsonResult) o);
        Workbook workbook = ExcelExportUtil.exportExcel(
                // TODO title,sheetName 导出实体类型参数化
                new ExportParams("title", "sheetName"),
                // TODO 导出实体类型参数化
                User.class,
                data);
        workbook.write(outputMessage.getBody());
    }

    private Collection getActualData(JsonResult result) {
        if (result != null && result.getPayload() != null) {
            Object data = result.getPayload();
            if (data instanceof PageInfo) {
                return ((PageInfo) data).getList();
            } else if (!(data instanceof Collection)) {
                return Lists.newArrayList(data);
            } else {
                return (Collection) data;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean canRead(@NonNull Type type, Class<?> contextClass, MediaType mediaType) {
        return false;
    }

    @Nullable
    @Override
    public Object read(@NonNull Type type, Class<?> contextClass, @NonNull HttpInputMessage inputMessage) {
        return null;
    }

    @Override
    public boolean canWrite(Type type, @NonNull Class<?> clazz, MediaType mediaType) {
        return super.canWrite(mediaType) && clazz == JsonResult.class;
    }


    @Override
    public void write(@NonNull Object o, Type type, MediaType contentType, @NonNull HttpOutputMessage outputMessage) throws IOException {
        super.write(o, contentType, outputMessage);
    }

}
