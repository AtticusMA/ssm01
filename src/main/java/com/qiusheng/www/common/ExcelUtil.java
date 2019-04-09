package com.qiusheng.www.common;



import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.*;
import com.qiusheng.www.ExcelFiled;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.reflect.MethodUtils.invokeMethod;


/**
* @Description:    用于excel的导出和导入
* @Author:         qiusheng
* @Company:
* @department:
* @CreateDate:     2019/3/26 17:20
* @UpdateUser:     qiusheng
* @UpdateDate:     2019/3/26 17:20
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ExcelUtil {
    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 工作簿对象
     */
    private SXSSFWorkbook sxssfWorkbook;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     *行号
     */
    private int rowNum;

    /**
     *注释类对象和获取属性list
     */
    List<Object[]> annotationList = Lists.newArrayList();


    /**
     *
     * @param title excel标题
     * @param t 实体类
     * @param type 导入导出类型
     * @param groups 分组
     */
    public ExcelUtil(String title, Class<?> t,int type,int... groups ){
        Field[] fs = t.getDeclaredFields();
        for(Field field:fs){
            ExcelFiled ef =field.getAnnotation(ExcelFiled.class);
            if(ef != null &&(ef.type()==0|| ef.type()==type )){
                if(groups.length>0 && groups !=null){
                    //这个分组是如何使用的，是不是将属性放到一起啊
                    boolean ingroup = false;
                    for(int g:groups){
                        if(ingroup){
                            break;
                        }
                        for(int ag:ef.groups()){
                            if(ag==g){
                                ingroup=true;
                                //list是有序的，按照group的标识顺序装载
                                annotationList.add(new Object[]{ef,fs});
                                break;
                            }
                        }
                    }
                }else{
                    annotationList.add(new Object[]{ef,fs});
                }
            }
        }
        Method[] methods = t.getMethods();
        for(Method m:methods){
            ExcelFiled ef =m.getAnnotation(ExcelFiled.class);
            if(ef != null &&(ef.type()==0|| ef.type()==type )){
                if(groups.length>0 && groups !=null){
                    //这个分组是如何使用的，是不是将属性放到一起啊
                    boolean ingroup = false;
                    for(int g:groups){
                        if(ingroup){
                            break;
                        }
                        for(int ag:ef.groups()){
                            if(ag==g){
                                ingroup=true;
                                //list是有序的，按照group的标识顺序装载
                                annotationList.add(new Object[]{ef,fs});
                                break;
                            }
                        }
                    }
                }else{
                    annotationList.add(new Object[]{ef,fs});
                }
            }
        }
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((ExcelFiled)(o1[0])).sort()).compareTo(((ExcelFiled)(o2[0])).sort());
            }
        });

        List<String> headerList = Lists.newArrayList();

        for(Object[] o:annotationList){
            String headerTitle = ((ExcelFiled)(o[0])).title();
            if(type==1){
                String[] ss= StringUtils.split(headerTitle,"**",2);
                if(ss.length >2){
                    headerTitle=ss[0];
                }
            }
            headerList.add(headerTitle);
        }
        initialize(title,headerList);

    }

    private void initialize(String title, List<String> headerList) {
        this.sxssfWorkbook=new SXSSFWorkbook(500);
        this.sheet=sxssfWorkbook.createSheet("Export");
        this.styles=createStyle(sxssfWorkbook);
        if(StringUtils.isNotBlank(title)){
            Row titleRow = sheet.createRow(rowNum++);
            titleRow.setHeightInPoints(30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(styles.get("title"));
            titleCell.setCellValue(title);
            //这个是做什么用
            sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),titleRow.getRowNum(),titleRow.getRowNum(),headerList.size()-1));
        }
        if(headerList == null){
            throw new RuntimeException("headerList not null");
        }
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.setHeightInPoints(16);
        //headerList.stream().mapToInt(i->);
        IntStream.range(0, headerList.size())
                .mapToObj(i -> {Cell cell = headerRow.createCell(i);
                cell.setCellStyle(styles.get("header"));
                String[] ss=StringUtils.split(headerList.get(i),"**",2);
                if(ss.length==2){
                    cell.setCellValue(ss[0]);
                    Comment comment=this.sheet.createDrawingPatriarch().createCellComment(
                            new XSSFClientAnchor(0,0,0,0,(short)3,3,(short)5,6));
                    comment.setString(new XSSFRichTextString(ss[1]));
                    cell.setCellComment(comment);
                }else{
                    cell.setCellValue(headerList.get(i));
                }
                sheet.autoSizeColumn(i);
                int colWidth=sheet.getColumnWidth(i)*2;
                sheet.setColumnWidth(i,colWidth<3000?3000:colWidth);
                return cell;});
        log.debug("Initialize success");

    }


    private Map<String,CellStyle> createStyle(SXSSFWorkbook sxssfWorkbook){
        Map<String,CellStyle> cellStyleMap=new HashMap<String, CellStyle>();
        //创建标题cell的格式
        CellStyle titleStyle=sxssfWorkbook.createCellStyle();
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font titleFont =sxssfWorkbook.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleStyle.setFont(titleFont);
        cellStyleMap.put("title",titleStyle);

        //创建数据格式
        CellStyle dataStyle=sxssfWorkbook.createCellStyle();
        dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        dataStyle.setBorderRight(CellStyle.BORDER_THIN);
        dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
        dataStyle.setBorderTop(CellStyle.BORDER_THIN);
        dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
        dataStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        dataStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = sxssfWorkbook.createFont();
        dataFont.setFontName("Arial");
        dataFont.setFontHeightInPoints((short)15);
        dataStyle.setFont(dataFont);
        cellStyleMap.put("data",dataStyle);


        //创建对齐方式为靠左的数据格式
        CellStyle leftDataStyle=sxssfWorkbook.createCellStyle();
        leftDataStyle.cloneStyleFrom(cellStyleMap.get("data"));
        leftDataStyle.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyleMap.put("alignLeftData",leftDataStyle);


        //创建headerTitle格式
        CellStyle headerTileStyle =sxssfWorkbook.createCellStyle();
        headerTileStyle.cloneStyleFrom(cellStyleMap.get("data"));
        headerTileStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerTileStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerTileFont = sxssfWorkbook.createFont();
        headerTileFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerTileFont.setColor(IndexedColors.WHITE.getIndex());
        headerTileStyle.setFont(headerTileFont);
        cellStyleMap.put("header",headerTileStyle);

        return cellStyleMap;
    }

    public <E>  ExcelUtil setDateList(List<E> list){
        for(E e:list){
            int column=0;
            Row row=sheet.createRow(rowNum++);
            StringBuilder stringBuilder =new StringBuilder();
            for(Object[] o:annotationList){
                ExcelFiled ef=(ExcelFiled)o[1];
                Object val = null;
                //通过反射获取属性值，需要判断注释的是属性还是方法，还有注释是否直接声明属性名
                try{
                    if(StringUtils.isNotBlank(ef.value())){
                        val=invokeClassGetter(e,ef.value());
                    }else{
                        if(o[1] instanceof Field){
                            val=invokeClassGetter(e,((Field)o[1]).getName());
                        }else if(o[1] instanceof Method){
                            val=invokeMethod(e,((Method)o[1]).getName(),new Class[]{},new Object[]{});
                        }
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                    log.info(ex.toString());
                }
                addCell(row,column++,val,ef.align(),ef.fieldType());
                stringBuilder.append(val+",");
            }
            log.debug("write success:{"+row.getRowNum()+"}"+stringBuilder.toString());
        }
        return this;
    }

    /**
     * 执行getter方法获取值
     * @param obj
     * @param methodname
     * @return
     */
    private Object invokeClassGetter(Object obj, String methodname) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object object=obj;
        for(String name:StringUtils.split(methodname,".")){
            String getterName = "get"+StringUtils.capitalize(name);
            //这个invokeMethod不知道是否能执行，方法参数没有类，没有参数值
            object=invokeMethod(object,getterName,new Class[]{},new Object[]{});
        }
        return object;
    }


    /**
     * 增加一个cell
     * @param row
     * @param column
     * @param val
     * @param align
     * @param fieldType
     */
    private void addCell(Row row, int column, Object val, int align, Class<?> fieldType) {
        Cell cell=row.createCell(column);
        String cellFormatString="@";
        try{
            if(val==null){
                cell.setCellValue("");
            }else if(fieldType != Class.class){
                cell.setCellValue((String)fieldType.getMethod("setValue",Object.class).invoke(null,val));
            }else{
                if(val instanceof String){
                    cell.setCellValue((String)val);
                }else if(val instanceof Integer){
                    cell.setCellValue((Integer)val);
                    cellFormatString = "0";
                }else if(val instanceof Long){
                    cell.setCellValue((Long)val);
                    cellFormatString="0";
                }else if(val instanceof Double){
                    cell.setCellValue((Double)val);
                    cellFormatString="0.00";
                }else if(val instanceof Float){
                    cell.setCellValue((Float)val);
                    cellFormatString="0.00";
                }else if(val instanceof Date){
                    cell.setCellValue((Date)val);
                    cellFormatString="yyyy-MM-dd";
                }else{
                    //这个很牛逼啊，不需要定义什么class，直接输出内容
                    cell.setCellValue((String)Class.forName(this.getClass().getName().replaceAll(this.getClass().getSimpleName(),
                            "fieldtype."+val.getClass().getSimpleName()+"Type")).getMethod("setValue", Object.class).invoke(null, val));
                }
                if (val != null){
                    CellStyle style = styles.get("data_column_"+column);
                    if (style == null){
                        style = sxssfWorkbook.createCellStyle();
                        style.cloneStyleFrom(styles.get("data"+(align>=1&&align<=3?align:"")));
                        style.setDataFormat(sxssfWorkbook.createDataFormat().getFormat(cellFormatString));
                        styles.put("data_column_" + column, style);
                    }
                    cell.setCellStyle(style);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
