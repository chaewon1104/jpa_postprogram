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
import com.spring.jpa.domain.userLogin;
import com.spring.jpa.domain.BoardRepository;
import com.spring.jpa.domain.PostRepository;
import com.spring.jpa.domain.loginInfo;
//import com.spring.jpa.domain.LoginRepository;
//import com.spring.jpa.domain.Login_info;
import com.spring.jpa.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardRepository boardRepository;
	@Autowired
	PostRepository postRepository;
	// @Autowired
	// LoginRepository loginrepository;

	@Autowired
	BoardService boardService;

	userLogin userlogin=new userLogin();
	
	
	
	// 게시판 프로그램 첫 화면
	@RequestMapping(value = "/boardListView", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardIndex(Model model) {

		List<post> post=postRepository.findAll();
//		List<loginInfo> listBoard = boardRepository.findAll();
//		List<loginInfofo> listBoard = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "bbsno"));
		
		model.addAttribute("post", post);

		return "boardListView";
	}

	/*
	 * public Sort sortByIdAsc() { return new Sort(Sort.Direction.ASC,"bbsno"); }
	 */


	
	
	
	
	
	// 게시글 작성 화면 이동 컨트롤러
	@RequestMapping(value = "/boardWriteView", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardWriteView() {

		return "boardWriteView";
	}

	
	
	// 게시글 db등록 컨트롤러
	@RequestMapping(value = "/boardWriteAction", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardWriteAction(HttpServletRequest req) {

		post Post = new post();
		Post.setBbsno(Integer.parseInt(req.getParameter("bbsno")));
		Post.setContentt(req.getParameter("contentt"));
		Post.setDaydate(req.getParameter("daydate"));
		Post.setTitle(req.getParameter("title"));
		Post.setWriter(req.getParameter("writer"));

		postRepository.save(Post);

		return "redirect:/boardListView";
	}

	
	
	// 게시글보기 컨트롤러
	@RequestMapping(value = "/boardReadView", method = { RequestMethod.POST })
	public String boardReadView(Model model,HttpServletRequest req) {
		int bbsno=Integer.parseInt(req.getParameter("bbsno"));
		post Post = postRepository.getOne(Integer.parseInt(req.getParameter("bbsno")));
		System.out.println(Post.getWriter());
		model.addAttribute("postenty", Post);
		return "boardReadView.html";
	}
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

	private void alert(String string) {
		// TODO Auto-generated method stub
		
	}

	//회원가입창으로 넘어가는 화면
	@RequestMapping(value = "/GotoClientRegistration", method = { RequestMethod.POST, RequestMethod.GET })
    public String GotoClientRegistration(Model model) {
        return "boardClientRegister.html";
    }
	
	//회원가입 이후 넘어가는 화면
	@RequestMapping(value = "/registClientAction", method = { RequestMethod.POST, RequestMethod.GET })
	public String registClientAction(HttpServletRequest req) {

		
		loginInfo logininfo=new loginInfo();
		System.out.println(req.getParameter("idd"));
	
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


	
	//로그인 확인 
	@RequestMapping(value = "/checkLogin", method = { RequestMethod.POST, RequestMethod.GET })
  public String checkLogin(HttpServletRequest req,Model model) {
		
	  String idd=req.getParameter("idd");
	  String passwd=req.getParameter("passwd");	  
	  System.out.println(Integer.parseInt(idd));
	  loginInfo logstate= boardRepository.getOne(Integer.parseInt(idd));
	  //List<loginInfo> logininfo=boardRepository.findByIddAndPasswd(idd, passwd);
	  Long size=boardRepository.countByIddAndPasswd(idd, passwd);
	 
	  if(size==0)
	  {		 
		  return "GotoLogIn.html";
	  }else 
	  {
      userlogin.setLogin(true);
      userlogin.setWriter(logstate.getNm());
      model.addAttribute("logininfo", logstate);
	  model.addAttribute("msg","로그인완료");
	  
//	  model.addAttribute("login1",userlogin.setLogin(true));
//	  model.addAttribute("login2",userlogin.getWriter(logininfo.));
	  }
      return "redirect:/boardListView";
  }
	
	

}