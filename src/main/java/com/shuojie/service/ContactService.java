package com.shuojie.service;

import com.shuojie.domain.Contact;
import com.shuojie.utils.vo.Result;

public interface ContactService {

    //添加留言
    Result insertContact(Contact contact);
}
