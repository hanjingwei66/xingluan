package com.shuojie.controller;

import com.shuojie.domain.Contact;
import com.shuojie.service.ContactServer;
import com.shuojie.utils.vo.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Resource(name = "contactServerImpl")
    private ContactServer contactServer;

    @RequestMapping(value = "/insertContact",method = RequestMethod.POST)
    public Result insertContact(@RequestBody Contact contact){
        Result result = contactServer.insertContact(contact);
        return result;
    }
}
