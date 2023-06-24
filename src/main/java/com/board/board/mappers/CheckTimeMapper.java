package com.board.board.mappers;

import org.apache.ibatis.annotations.Mapper;

// MyBatis Connection Check 
// CheckTimeMapper
@Mapper
public interface CheckTimeMapper {

    // MySql timeCheck
    String getTime();
}
