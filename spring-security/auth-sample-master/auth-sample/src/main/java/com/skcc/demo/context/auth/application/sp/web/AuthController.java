package com.skcc.demo.context.auth.application.sp.web;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skcc.demo.context.auth.domain.authority.AuthorityService;
import com.skcc.demo.context.auth.domain.authority.account.AccountRepository;
import com.skcc.demo.context.auth.domain.authority.account.model.Account;
import com.skcc.demo.context.auth.domain.authority.permission.PermissionRepository;
import com.skcc.demo.context.auth.domain.authority.permission.model.Permission;
import com.skcc.demo.context.auth.domain.authority.role.RoleRepository;
import com.skcc.demo.context.auth.domain.authority.role.model.Role;
import com.skcc.demo.context.auth.domain.authority.role.model.RoleDivision;
import com.skcc.demo.context.bcm.domain.functions.FunctionsService;
import com.skcc.demo.context.bcm.domain.functions.menu.SubMenuRepository;
import com.skcc.demo.context.bcm.domain.functions.menu.model.TopMenu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@AllArgsConstructor
public class AuthController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private AuthorityService authorityService;
	private FunctionsService functionsService;
	
	/*
	 * @secured test
	
	
	 //여러개의 Role선언 가능@Secured({"ROLE_SYS_ADMIN", "ROLE_COUNSELOR"}) 
	@GetMapping("/secure/test")
	public String checkAuth( Model model) {
		
		 try {
			 User account = authorityService.getUserInfo();
			 
			 log.info("Account == {}.", account);
			 model.addAttribute("account", account );
			 return "/secured";
		    
		 	} catch(AccessDeniedException e) {
		       log.warn("Unauthorized", e);
		       return "/denied";
		    }
		}
		
	*
	*/
	/*****************페이지 맵핑*********************/
	
	
	@GetMapping("/") //메인페이지
	public String index() {
		return "/index";
	}
	
	  // 회원가입 페이지
    @GetMapping("/signup")
    public String dispSignup() {
        return "/signup";
    }
    
    // 회원가입 처리
    @PostMapping("/signup")
    public String execSignup(Account member) {
        authorityService.joinUser(member);

        return "redirect:/login";
    }
    // 로그인 페이지
    @GetMapping("/login")
    public String dispLogin() {
        return "/login";
    }
    // 로그인 결과 페이지
    @GetMapping("/login/result")
    public String dispLoginResult(Model model) {
    	List<Long> subMenuIdList = authorityService.getSubMenuByPer();
    	List<TopMenu> topMenuList =functionsService.getTopMenuBySub(subMenuIdList);
    	model.addAttribute("topMenuList", topMenuList);
    	return "/index";
    }
    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "redirect:/login?logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
    //사용자 관리 페이지
    @GetMapping("/admin/usermanage")
    public String disManageUsers(@PageableDefault Pageable pageable, Model model) {
    	model.addAttribute("userList", authorityService.findAllUsers(pageable));
    	return "/users/list";
    }
    //권한관리페이지
    @GetMapping("/admin/permission")
    public String managePermission( Model model) 
    {	
    	return"/permission/list";
    }
    
  
   /***********************templates 내부 url 맵핑*************************/
    //사용자 관리 - 생성, 수정,삭제
    @GetMapping("/admin/usermanage/create")
    public String createUsers(@RequestParam(value = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("account", accountRepository.findById(id).orElse(new Account()));
        if(id!=0) {
        	Long roleId = accountRepository.findById(id).get().getRoleId();
        	if(roleId==null) {
        		model.addAttribute("role", null);
        	}else {
        	model.addAttribute("role", roleRepository.findById(accountRepository.findById(id).get().getRoleId()).get());
        	}
        }
        return "/users/form";	
    }
    //권한 수정 및 부여
    @GetMapping("/admin/permission/getperlist")
    public String getAllPermissions(@RequestParam(value ="roleId", defaultValue="0")Long roleId, Model model){
    	model.addAttribute("permissionList", permissionRepository.findAll());
    	model.addAttribute("role", roleRepository.findById(roleId).get());
    	model.addAttribute("resourceList", functionsService.getSubMenuList() );
    	
    	return "/permission/list";
    }
   
    @GetMapping("/admin/permission/create/role")
    public String createRole(@RequestParam(value="id", defaultValue="0")Long id, Model model) {
    	model.addAttribute("role",roleRepository.findById(id).orElse(new Role()));
    	return"/permission/form";
    }
    
    /*********************데이터 불러오기***************************/
    
    
    //권한 리스트가져오기
    @GetMapping("/permissions")
    @ResponseBody
    public List<Permission> getAllPermissions(@RequestParam(value="roleId", required=true)Long roleId) {
    	return authorityService.getPermissions();
    }
    
    
    //역할 리스트 가져오기
    @GetMapping("/roles")
    @ResponseBody
    public List<Role> getAllRoles(@RequestParam(value="roleDivision", required=true) RoleDivision roleDivision) {
    	return authorityService.getRoles(roleDivision);
    }
    
    //역할 사용여부 확인하기
    @GetMapping("/check/roles")
    @ResponseBody
    public boolean checkRole(@RequestParam(value="roleName", required=true) String roleName) {
    	return authorityService.checkRoles(roleName);
    }

}
