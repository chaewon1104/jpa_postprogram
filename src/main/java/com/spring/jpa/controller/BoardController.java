package com.spring.jpa.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.jpa.domain.post;
import com.spring.jpa.domain.BoardRepository;
import com.spring.jpa.domain.loginInfo;
//import com.spring.jpa.domain.LoginRepository;
//import com.spring.jpa.domain.Login_info;
import com.spring.jpa.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardRepository boardRepository;
	// @Autowired
	// LoginRepository loginrepository;

	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/boardListView", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardIndex(Model model) {

		List<post> post=
		List<loginInfo> listBoard = boardRepository.findAll();
//		List<loginInfofo> listBoard = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "bbsno"));
		model.addAttribute("listBoard", listBoard);

		return "boardListView";
	}

	/*
	 * public Sort sortByIdAsc() { return new Sort(Sort.Direction.ASC,"bbsno"); }
	 */

	@RequestMapping(value = "/boardWriteView", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardWriteView() {

		return "boardWriteView";
	}

//	@RequestMapping(value = "/boardWriteAction", method = { RequestMethod.POST, RequestMethod.GET })
//	public String boardWriteAction(HttpServletRequest req) {
//
//		Board board = new Board();
//		board.setSubject(req.getParameter("subject"));
//		board.setWname(req.getParameter("wname"));
//		board.setContent(req.getParameter("content"));
//		board.setPasswd("123");
//		board.setReadcnt(0);
//
//		boardRepository.save(board);
//
//		return "redirect:/boardListView";
//	}
//
//	@RequestMapping(value = "/boardReadView", method = { RequestMethod.POST })
//	public String boardReadView(Model model, HttpServletRequest req) {
//
//		Board board = boardRepository.getOne(Integer.parseInt(req.getParameter("bbsno")));
//		model.addAttribute("boardEnty", board);
//
//		return "boardReadView";
//	}
//	
//	@RequestMapping(value = "/boardListViewPaging", method = { RequestMethod.POST, RequestMethod.GET })
//    public String boardIndexPaging(@PageableDefault Pageable pageable, Model model) {
//		
//        Page<Board> boardList = boardService.getBoardList(pageable);
//        //Page<Board> boardList = boardService.getBoardList(Sort.by(Sort.Direction.DESC, "bbsno"));
//
//        model.addAttribute("listBoard", boardList);
//
//        return "boardListViewPaging";
//    }

	//회원가입창으로 넘어가는 화면
	@RequestMapping(value = "/GotoClientRegistration", method = { RequestMethod.POST, RequestMethod.GET })
    public String GotoClientRegistration(Model model) {
        return "boardClientRegister.html";
    }
	
	//회원가입 이후 넘어가는 화면
	@RequestMapping(value = "/registClientAction", method = { RequestMethod.POST, RequestMethod.GET })
	public String registClientAction(HttpServletRequest req) {

		
		loginInfo logininfo=new loginInfo();
		
	
		logininfo.setNm(req.getParameter("nm"));
		logininfo.setBirthday(req.getParameter("birthday"));
		logininfo.setIdd(req.getParameter("idd"));
		logininfo.setPasswd(req.getParameter("passwd"));

		logininfo.setPhonenum(req.getParameter("phonenum"));
		

		boardRepository.save(logininfo);

		return "redirect:/boardListView";
	}
	
//	로그인화면으로 넘어가는 화면
	@RequestMapping(value = "/GotoLogIn", method = { RequestMethod.POST, RequestMethod.GET })
    public String GotoLogIn(Model model) {
        return "GotoLogIn.html";
    }

//	@RequestMapping(value = "/checkLogin", method = { RequestMethod.POST, RequestMethod.GET })
//    public String checkLogin(String idd,String passwd,Model model) {
//			
//        return "boardListView.html";
//    }
	

	
	
	//로그인 확인 
	@RequestMapping(value = "/checkLogin", method = { RequestMethod.POST, RequestMethod.GET })
  public String checkLogin(HttpServletRequest req,Model model) {
		
	  String idd=req.getParameter("idd");
	  String passwd=req.getParameter("passwd");
	  
	List<loginInfo> logininfo=boardRepository.findByIddAndPasswd(idd, passwd);
	Long count=boardRepository.countByIddAndPasswd(idd, passwd);
	
	  if(count==0)
	  {		 
		  return "GotoLogIn.html";
	  }else
	  { model.addAttribute("logininfo", logininfo);
	  model.addAttribute("msg","로그인완료"); }
      return "boardListView";
  }
	
	

}