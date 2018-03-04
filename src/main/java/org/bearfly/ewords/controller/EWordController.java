package org.bearfly.ewords.controller;

import javax.annotation.Resource;

import org.bearfly.ewords.model.Word;
import org.bearfly.ewords.service.IEWordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EWordController {
    private IEWordService ewService;

    @RequestMapping("/test")
    public String test() {
        System.out.println(ewService.getAllEwords().size());
        return "demo";
    }

    @ResponseBody
    @RequestMapping("/ewords")
    public String getEwordsList() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(ewService.getAllEwords());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
    
    @ResponseBody
    @RequestMapping("/eword")
    public String getEword(String eword, String cword) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(ewService.getWord(new Word(null, eword, cword)));
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
    @ResponseBody
    @RequestMapping(value = "/delword", method = RequestMethod.POST)
    public String delEwordsList(int id) {
        Boolean result = ewService.delEword(new Word(id,null,null));
        
        return result?"success":"failed";
    }
    
    @ResponseBody
    @RequestMapping(value = "/addword", method = RequestMethod.POST)
    public String delEwordsList(String eword, String cword) {
        Boolean result = ewService.addEword(new Word(eword, cword));
       
        return result?"success":"failed";
    }
    
    public IEWordService getEwService() {
        return ewService;
    }

    @Resource(name = "ewService")
    public void setEwService(IEWordService ewService) {
        this.ewService = ewService;
    }
}
