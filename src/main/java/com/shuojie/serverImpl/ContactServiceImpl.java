package com.shuojie.serverImpl;

import com.shuojie.dao.ContactMapper;
import com.shuojie.domain.Contact;
import com.shuojie.service.ContactService;
import com.shuojie.utils.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

    @Resource
    private ContactMapper contactMapper;

    private Result result;

    @Override
    public Result insertContact(Contact contact) {
        contactMapper.insertContact(contact);
        String contactText = contact.getContactText();
        if (contactText != null){
            result = new Result(200,"contactSuccess","insertContact");
        }else {
            result = new Result(201,"contactError","insertContact");
        }
        return result;
    }
}
