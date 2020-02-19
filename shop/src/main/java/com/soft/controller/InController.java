package com.soft.controller;

import com.soft.pojo.In;
import com.soft.pojo.Out;
import com.soft.pojo.Ware;
import com.soft.service.InServer;
import com.soft.service.WareServer;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class InController {
    @Autowired
    private InServer inServer;

    @Autowired
    private WareServer wareServer;

    @RequestMapping("/in/inlist")
    public String inlist(Model model){
        List<In> ins = inServer.selectIn();
        model.addAttribute("ins",ins);
        return "/in/inlist";
    }

    @RequestMapping("/in/addlist")
    public String addlist(Model model){
        List<In> ins = inServer.selectIn();
        model.addAttribute("ins",ins);
        return "/in/addlist";
    }

    @RequestMapping("in/updatelist")
    public String updatelist(Model model){
        List<In> ins = inServer.selectInbystatus(0);
        model.addAttribute("ins",ins);
        return "/in/updatelist";
    }

    @RequestMapping("/in/deletelist")
    public String deletelist(Model model){
        List<In> ins = inServer.selectInbystatus(0);
        model.addAttribute("ins",ins);
        return "/in/deletelist";
    }

    @RequestMapping("/in/toadd")
    public String toadd(Model model){
        List<Ware> wares = wareServer.selectWare();
        model.addAttribute("wares",wares);
        return "in/in-add";
    }

    @RequestMapping("/in/addIn")
    public String addIn(In in){
        In in_a = inServer.selectInbyname(in.getIn_ware(),in.getIn_status());
        if (in_a==null){
            inServer.addIn(in);
        }else {
            in_a.setIn_price(in.getIn_price());
            in_a.setIn_reason(in.getIn_reason());
            in_a.setIn_time(in.getIn_time());
            in_a.setIn_user(in.getIn_user());
            inServer.updateInbyname(in_a);
        }
        //Ware wares = wareServer.selectWarebyname(out.getOut_ware());
        //wares.setWare_out(out.getOut_price());
        //wareServer.updateWare(wares);
        return "redirect:/in/addlist";
    }

    @RequestMapping("/in/toupdate/{id}")
    public String toupdate(@PathVariable("id") int id, Model model){
        In in = inServer.selectInbyid(id);
        model.addAttribute("in",in);
        return "in/in-update";
    }

    @RequestMapping("/in/updateIn")
    public String updatein(In in){
        inServer.updateInbyid(in);
        return "redirect:/in/updatelist";
    }

    @ResponseBody
    @RequestMapping("/in/deletein/{id}")
    public String deletein(@PathVariable("id") int id){
        inServer.deleteIn(id);
        return "ok";
    }
}
