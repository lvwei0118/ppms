package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.usual.bean.Archives;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:42
 * @project: ppms
 * @Description: 档案管理
 */
public interface ArchivesServiceInter {

    int uploadAInfo(HSSFSheet sheet);

    PageInfo<Archives> listAInfo(String pageNum);

    Archives findAInfoById(Integer id);
}
