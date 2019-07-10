package com.ujiuye.auth.mapper;

import com.ujiuye.auth.bean.Sources;
import com.ujiuye.auth.bean.SourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SourcesMapper {
    int countByExample(SourcesExample example);

    int deleteByExample(SourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sources record);

    int insertSelective(Sources record);

    List<Sources> selectByExample(SourcesExample example);

    Sources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByExample(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByPrimaryKeySelective(Sources record);

    int updateByPrimaryKey(Sources record);
    /*查询二级权限信息*/
    List<Sources> getSecondSourcesByEid(Integer eid);

    List<Sources> selectChildren(@Param("pid") Integer pid,@Param("eid") Integer eid);
}