package com.shuojie.dao;

import com.shuojie.domain.Contact;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactMapper {

    void insertContact(Contact contact);
}