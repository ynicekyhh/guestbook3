package com.bigdata2017.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigdata2017.guestbook.dao.GuestbookDao;
import com.bigdata2017.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping( "/" )
	public String index(Model model) {
		List<GuestbookVo> list = guestbookDao.getList();
		model.addAttribute("list", list);
		return "/WEB-INF/views/index.jsp";
	}
	
	/* check!!!! */
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookDao.insert(guestbookVo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete() {
		return "/WEB-INF/views/deleteform.jsp";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookDao.delete(guestbookVo.getNo(), guestbookVo.getPassword());
		return "redirect:/";
	}
}
