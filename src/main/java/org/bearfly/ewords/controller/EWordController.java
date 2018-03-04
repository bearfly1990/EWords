package org.bearfly.ewords.controller;

import javax.annotation.Resource;

import org.bearfly.ewords.service.IEWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EWordController {
    private IEWordService ewService;
    @RequestMapping("/test")
    public String test(){
        System.out.println(ewService.getAllEwords().size());
        return "demo";
    }
    public IEWordService getEwService() {
        return ewService;
    }
    @Resource(name="ewService")
    public void setEwService(IEWordService ewService) {
        this.ewService = ewService;
    }
}
