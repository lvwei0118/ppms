package com.ujiuye.cus.controller;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.service.CustomerServiceInter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:30
 * @project: ppms
 * @Description: 客户相关信息
 */

@Controller
@RequestMapping("/cus")
public class CustomerController {
    @Autowired
    private CustomerServiceInter customerServiceInter;
    /*添加客户信息*/
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Customer customer) {
        boolean result = customerServiceInter.saveInfo(customer);
        if (result) {
            return "redirect:/customer.jsp";
        }else {
            return "redirect:/customer-add.jsp";
        }
    }

    /*查询客户信息*/
    @RequestMapping(value = "listInfo",method = RequestMethod.GET)
    @ResponseBody
    public  List<Customer> listInfo() {
       return customerServiceInter.listInfo();
    }
    /*查询单个客户详情*/
    @RequestMapping(value = "findInfoById/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView findInfoById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/customer-look");
        Customer infoById = customerServiceInter.findInfoById(id);
        mv.addObject("customer",infoById);
        return mv;
    }
    /*修改单个客户详情*/
     @RequestMapping(value = "findById/{id}",method = RequestMethod.GET)
     @ResponseBody
     public ModelAndView findById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("/customer-edit");
        Customer infoById = customerServiceInter.findInfoById(id);
        mv.addObject("customer",infoById);
        return mv;
    }
    @RequestMapping(value = "updateInfo",method = RequestMethod.POST)
    public String updateInfo(Customer customer) {
        boolean updateInfo = customerServiceInter.updateInfo(customer);
        if (updateInfo) {
            return "redirect:/customer.jsp";
        }else {
            return "redirect:/customer-edit.jsp";
        }
    }
    /*删除客户*/
    @RequestMapping(value = "delInfo/{ids}",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> delInfo(@PathVariable("ids")String ids) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean result=customerServiceInter.delInfo(ids);
            map.put("statusCode",200);
            map.put("message","success");
        }catch (Exception e){
            map.put("statusCode",500);
            map.put("message",e.getMessage());
        }
        return map;
    }
    /*条件查询客户信息*/
    @RequestMapping(value = "selectInfo",method = RequestMethod.GET)
    @ResponseBody
    public /*ModelAndView*/List<Customer> selectInfo(int chid,String infoKey,int orderWord) {
       return customerServiceInter.selectInfo(chid,infoKey,orderWord);
        /*ModelAndView mv = new ModelAndView();
        List<Customer> list = customerServiceInter.selectInfo();
        mv.addObject("customerList",list);
        mv.setViewName("/customer");
        return mv;*/
    }
    /*导出Excel*/
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exportExcel() throws Exception{
        //用list集合盛放从数据库取出来的目标对象列表
        List<Customer> list = customerServiceInter.listInfo();
        Workbook wb=new HSSFWorkbook();
        Sheet customers1 = wb.createSheet("customers");
        HSSFRow row =(HSSFRow)customers1.createRow(0);
        //第一行中有几个字段
        HSSFCell cell[] = new HSSFCell[9];
        for(int i = 0; i < cell.length; i++){
            //取第一行第一列
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("Id");
        cell[1].setCellValue("公司名称");
        cell[2].setCellValue("公司联系人");
        cell[3].setCellValue("公司地址");
        cell[4].setCellValue("联系人电话");
        cell[5].setCellValue("公司座机");
        cell[6].setCellValue("公司简介");
        cell[7].setCellValue("备注");
        cell[8].setCellValue("添加时间");


        for(int i = 0; i < list.size(); i++){
            Customer customer = list.get(i);
            Row row1 = customers1.createRow(i + 1);
            //创建盛放所有列的数组
            HSSFCell dataCell[] = new HSSFCell[9];
            for(int j = 0; j < dataCell.length; j++){
                //取得第一行所有列
                dataCell[j] =(HSSFCell)row1.createCell(j);
            }
            dataCell[0].setCellValue(customer.getId());
            dataCell[1].setCellValue(customer.getComname());
            dataCell[2].setCellValue(customer.getCompanyperson());
            dataCell[3].setCellValue(customer.getComaddress());
            dataCell[4].setCellValue(customer.getComphone());
            dataCell[5].setCellValue(customer.getCamera());
            dataCell[6].setCellValue(customer.getRemark());
            dataCell[7].setCellValue(customer.getRemark());
            dataCell[8].setCellValue(customer.getAddtime());

            //创建样式
            HSSFCellStyle cellStyle = (HSSFCellStyle)wb.createCellStyle();
            //日期
            HSSFDataFormat format=(HSSFDataFormat)wb.createDataFormat();
            cellStyle.setDataFormat(format.getFormat("yyyy年MM月dd日"));
            dataCell[8].setCellStyle(cellStyle);

        }
        wb.write(new FileOutputStream(new File("D:\\Desktop\\customer.xlsx")));
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode",200);
        map.put("msg","导出成功");
        return  map;
    }


}
