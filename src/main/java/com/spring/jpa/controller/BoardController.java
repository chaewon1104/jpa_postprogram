package com.spring.jpa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import javax.xml.ws.Response;

import org.dom4j.util.StringUtils;
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
	SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Date time = new Date();
	String whattime=format1.format(time); 
	
	
	
	// 게시판 프로그램 첫 화면
	@RequestMapping(value = "/boardListView", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardIndex(Model model, HttpSession session) {

		List<post> post=postRepository.findAll();
//		List<loginInfo> listBoard = boardRepository.findAll();
//		List<loginInfofo> listBoard = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "bbsno"));
		
		model.addAttribute("post", post);		
		model.addAttribute("loginName", session.getAttribute("loginName"));
		System.out.println("확인");
		System.out.println(session.getAttribute("loginName"));

		return "boardListView";
	}

	/*
	 * public Sort sortByIdAsc() { return new Sort(Sort.Direction.ASC,"bbsno"); }
	 */


	
	
	
	
	
	// 게시글 작성 화면 이동 컨트롤러
	@RequestMapping(value = "/boardWriteView", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardWriteView(HttpSession session,Model model) {
		model.addAttribute("loginName",session.getAttribute("loginName"));		
		return "boardWriteView";
	}

	
	
	// 게시글 db등록 컨트롤러
	@RequestMapping(value = "/boardWriteAction", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardWriteAction(HttpServletRequest req,HttpSession session) {

		
		
		
		post Post = new post();
		Post.setBbsno(Integer.parseInt(req.getParameter("bbsno")));
		Post.setContentt(req.getParameter("contentt"));
		Post.setDaydate(whattime);
		System.out.println("제목 : " + req.getParameter("title"));
		Post.setTitle(req.getParameter("title"));
		System.out.println("작성자 : " + req.getParameter("writer"));
		Post.setWriter(session.getAttribute("loginName").toString());
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
  public String checkLogin(HttpServletRequest req, HttpServletResponse response,
		  	Model model, HttpSession session) {
		
	  String idd=req.getParameter("idd");
	  String passwd=req.getParameter("passwd");	  

	  
	  Optional<loginInfo> logstate= boardRepository.findById(idd);
	  //List<loginInfo> logininfo=boardRepository.findByIddAndPasswd(idd, passwd);
	  Long size=boardRepository.countByIddAndPasswd(idd, passwd);
	  
	  System.out.println(logstate);
	  if(!logstate.isPresent())
	  {	  alert("해당하는 아이다가 없습니다.");
	  response.setContentType("text/html; charset=UTF-8");
      PrintWriter out;
	try {
		out = response.getWriter();
		out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);</script>");
	    out.flush();
	    return "redirect:/GotoLogIn";

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


      
	  }else 
	  {
      userlogin.setLogin(true);
      userlogin.setWriter(logstate.get().getNm());
      session.setAttribute("loginName", logstate.get().getNm());
	  }
      return "redirect:/boardListView";
  }
	
	

}