package com.ujiuye.cus.service;

import com.ujiuye.cus.bean.Customer;

import java.util.List;

/**
 * @Auther: lvwei
 * @Date: 2019/4/3 16:42
 * @project: ppms
 * @Description: TODO
 */
public interface CustomerServiceInter {
    boolean saveInfo(Customer customer);

    List<Customer> listInfo();

    Customer findInfoById(Integer id);

    boolean updateInfo(Customer customer);

    boolean delInfo(String ids);

    List<Customer> selectInfo(int chid,String infoKey,int orderWord);
}
