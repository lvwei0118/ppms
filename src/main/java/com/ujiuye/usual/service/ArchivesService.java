package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.mapper.CustomerMapper;
import com.ujiuye.usual.bean.Archives;
import com.ujiuye.usual.mapper.ArchivesMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 档案管理
 */
@Service
public class ArchivesService implements ArchivesServiceInter {

    @Resource
    private ArchivesMapper archivesMapper;
    /*添加档案信息（Excel上传）*/
    @Override
    @Transactional
    public int uploadAInfo(HSSFSheet sheet) {
        /*判断工作表里面有几行内容*/
        int rowNum = sheet.getLastRowNum();
        int i1=0;
        /*循环遍历工作表里面的所有行*/
        for (int i =1; i <= rowNum; i++) {
           Row row = sheet.getRow(i);
           /*循环遍历每一行内容*/
            /*判断工作表里面第一行有几个单元格*/
            int cellNum = row.getLastCellNum();
            StringBuffer bf = new StringBuffer();
            /*获取每一行的单元格*/
            for (int j=0;j<=cellNum;j++){
                Cell cell = row.getCell(j);
                String value=null;
                if(cell == null){
                    continue;
                }
                if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                    double cellValue = cell.getNumericCellValue();
                    value=Double.toString(cellValue);
                }else {
                  value = cell.getStringCellValue();


                }
                bf.append(value + "~");
            }
                String hang = bf.toString();
                String[] rows = hang.split("~");
                Archives archives = new Archives();
                archives.setDnum(rows[0]);
                archives.setLandline(rows[1]);
                archives.setSchool(rows[2]);
                archives.setZhuanye(rows[3]);
                archives.setSosperson(rows[4]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = sdf.parse(rows[5]);
                    archives.setBiyedate(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                archives.setZzmm(rows[6]);
                archives.setMinzu(rows[7]);
                archives.setXueli(rows[8]);
                archives.setEmail(rows[9]);
                Double d = new Double(rows[10]);
                int eid = d.intValue();
                archives.setEmpFk(eid);

                i1 = archivesMapper.insert(archives);
            }

        return i1;
    }
    /*查看档案*/
    @Override
    public PageInfo<Archives> listAInfo(String pageNum) {
        Integer pageNo=1;
        try{
            pageNo=Integer.parseInt(pageNum);
        }catch (Exception EX){}
        PageHelper.startPage(pageNo,3);
        List<Archives> list = archivesMapper.listAInfo();
        PageInfo<Archives> pageInfo = new PageInfo<>(list, 5);
        return pageInfo;
    }
    /*查看个人档案*/
    @Override
    public Archives findAInfoById(Integer eid) {

        return archivesMapper.findAInfoById(eid);
    }


}
