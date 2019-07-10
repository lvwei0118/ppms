package com.ujiuye.usual.mapper;


import com.ujiuye.usual.bean.Comparison;

import java.util.List;

public interface ComparisonMapper {

    int insert(Comparison comparison);

    List<Comparison> listCInfo();

    List<Comparison> compare();
}