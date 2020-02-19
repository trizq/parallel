package com.soft.controller;

import com.soft.pojo.Ware;
import com.soft.service.WareServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

@Controller
public class WareController {
    @Autowired
    private WareServer wareServer;

    @RequestMapping("/ware/warelist")
    public String wareselect(Model model){
       List<Ware> wares = wareServer.selectWare();
       model.addAttribute("wares",wares);
       return "/ware/warelist";
    }

    @RequestMapping("/ware/addlist")
    public String addlist(Model model){
        List<Ware> wares = wareServer.selectWare();
        model.addAttribute("wares",wares);
        return "/ware/addlist";
    }

    @RequestMapping("/ware/updatelist")
    public String updatelist(Model model){
        List<Ware> wares = wareServer.selectWare();
        model.addAttribute("wares",wares);
        return "/ware/updatelist";
    }

    @RequestMapping("/ware/deletelist")
    public String deletelist(Model model){
        List<Ware> wares = wareServer.selectWare();
        model.addAttribute("wares",wares);
        return "/ware/deletelist";
    }

    @RequestMapping("/ware/toadd")
    public String toadd(){
        return "/ware/ware-add";
    }

    @RequestMapping("/ware/toupdate/{id}")
    public String toupdate(@PathVariable("id")  int id,Model model){
        Ware ware = wareServer.selectWarebyid(id);
        model.addAttribute("ware",ware);
        return "/ware/ware-update";
    }

    @ResponseBody
    @RequestMapping("/ware/deleteware/{id}")
    public String deleteware(@PathVariable("id") int id){
        wareServer.deleteWare(id);
        return "ok";
    }

    @RequestMapping("/ware/addWare")
    public String addWare(Ware ware){
        wareServer.addWare(ware);
        return "redirect:/ware/addlist";
    }

    @RequestMapping("/ware/updateWare")
    public String updateWare(Ware ware){
        wareServer.updateWare(ware);
        return "redirect:/ware/updatelist";
    }
}
