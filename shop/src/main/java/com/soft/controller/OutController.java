package com.soft.controller;

import com.soft.pojo.Out;
import com.soft.pojo.Ware;
import com.soft.service.OutServer;
import com.soft.service.WareServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OutController {
    @Autowired
    private OutServer outServer;

    @Autowired
    private WareServer wareServer;

    @RequestMapping("/out/outlist")
    public String outlist(Model model){
        List<Out> outs= outServer.selectOut();
        model.addAttribute("outs",outs);
        return "/out/outlist";

    }

    @RequestMapping("/out/addlist")
    public String addlist(Model model){
        List<Out> outs= outServer.selectOut();
        model.addAttribute("outs",outs);
        return "/out/addlist";
    }

    @RequestMapping("/out/updatelist")
    public String updatelist(Model model){
        List<Out> outs= outServer.selectOutbystatus(0);
        model.addAttribute("outs",outs);
        return "/out/updatelist";
    }

    @RequestMapping("/out/deletelist")
    public String deletelist(Model model){
        List<Out> outs= outServer.selectOutbystatus(0);
        model.addAttribute("outs",outs);
        return "/out/deletelist";
    }

    @ResponseBody
    @RequestMapping("/out/deleteout/{id}")
    public String deleteout(@PathVariable("id") int id){
        outServer.deleteOut(id);
        return "ok";
    }
    @RequestMapping("/out/toadd")
    public String toadd(Model model){
        List<Ware> wares = wareServer.selectWare();
        model.addAttribute("wares",wares);
        return "out/out-add";
    }

    @RequestMapping("/out/toupdate/{id}")
    public String toupdate(@PathVariable("id") int id, Model model){
        Out out = outServer.selectOutbyid(id);
        model.addAttribute("out",out);
        return "out/out-update";
    }

    @RequestMapping("/out/updateOut")
    public String updateout(Out out){
        outServer.updateOutbyid(out);
        return "redirect:/out/updatelist";
    }

    @RequestMapping("/out/addOut")
    public String addOut(Out out){
        Out out_a = outServer.selectOutbyname(out.getOut_ware(),out.getOut_status());
        if (out_a==null){
            outServer.addOut(out);
        }else {
            out_a.setOut_price(out.getOut_price());
            out_a.setOut_reason(out.getOut_reason());
            out_a.setOut_time(out.getOut_time());
            out_a.setOut_user(out.getOut_user());
            outServer.updateOutbyname(out_a);
        }
        //Ware wares = wareServer.selectWarebyname(out.getOut_ware());
        //wares.setWare_out(out.getOut_price());
        //wareServer.updateWare(wares);
        return "redirect:/out/addlist";
    }
}
