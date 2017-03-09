package com.oec.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oec.base.BaseController;
import com.oec.demo.service.UserService;
import com.oec.model.User;

/**
 * 
 * @author liujie
 * @version 1.0
 * @since 2017楠烇拷2閺堬拷7閺冿拷 娑撳宕�4:28:58
 */
@Controller
public class HomeController extends BaseController {
	@Autowired
	private UserService serve;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) {
		model.addAttribute("user", new User());
		return "/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, BindingResult bindingResult, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		String ipstr="";
		try {
//			 ipstr=getIpAddr(request);
			 ipstr=request.getRemoteAddr();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (bindingResult.hasErrors()) {
			return "/login";
		}
		// 閸掓稑缂撻悽銊﹀煕閸氬秴鎷扮�靛棛鐖滈惃鍕姢閻楋拷
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		// 鐠佹澘缍嶇拠銉ゆ姢閻楀矉绱濇俊鍌涚亯娑撳秷顔囪ぐ鏇炲灟缁鎶�鐠愵厾澧挎潪锕�濮涢懗鎴掔瑝閼虫垝濞囬悽銊ｏ拷锟�
		token.setRememberMe(true);
		// subject閻炲棜袙閹存劖娼堥梽鎰嚠鐠灺帮拷鍌滆娴肩磫ser
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (UnknownAccountException ex) {// 閻€劍鍩涢崥宥嗙梾閺堝澹橀崚鑸拷锟�
			redirectAttributes.addFlashAttribute("message", "閻€劍鍩涢崥宥嗘弓閹垫儳鍩�");
			return "redirect:/login";
		} catch (IncorrectCredentialsException ex) {// 閻€劍鍩涢崥宥呯槕閻椒绗夐崠褰掑帳閵嗭拷
			redirectAttributes.addFlashAttribute("message", "閻€劍鍩涢崥宥嗗灗鐎靛棛鐖滈柨娆掝嚖");
			return "redirect:/login";
		} catch (AuthenticationException e) {// 閸忔湹绮惃鍕瑜版洟鏁婄拠锟�
			redirectAttributes.addFlashAttribute("message", "閸忔湹绮鍌氱埗闁挎瑨顕�");
			return "redirect:/login";
		}
		// 妤犲矁鐦夐弰顖氭儊閹存劕濮涢惂璇茬秿閻ㄥ嫭鏌熷▔锟�
		if (subject.isAuthenticated()) {
			serve.insertJK(user,ipstr);
			return "redirect:/home";
		}
		return null;
	}

	 /** 
	 * 閫氳繃HttpServletRequest杩斿洖IP鍦板潃 
	 * @param request HttpServletRequest 
	 * @return ip String 
	 * @throws Exception 
	 */  
	public String getIpAddr(HttpServletRequest request) throws Exception {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	} 
	/** 
	 * 閫氳繃IP鍦板潃鑾峰彇MAC鍦板潃 
	 * @param ip String,127.0.0.1鏍煎紡 
	 * @return mac String 
	 * @throws Exception 
	 */  
	public String getMACAddress(String ip) throws Exception {  
	    String line = "";  
	    String macAddress = "";  
	    final String MAC_ADDRESS_PREFIX = "MAC Address = ";  
	    final String LOOPBACK_ADDRESS = "127.0.0.1";  
	    //濡傛灉涓�127.0.0.1,鍒欒幏鍙栨湰鍦癕AC鍦板潃銆�  
	    if (LOOPBACK_ADDRESS.equals(ip)) {  
	        InetAddress inetAddress = InetAddress.getLocalHost();  
	        //璨屼技姝ゆ柟娉曢渶瑕丣DK1.6銆�  
	        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();  
	        //涓嬮潰浠ｇ爜鏄妸mac鍦板潃鎷艰鎴怱tring  
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 0; i < mac.length; i++) {  
	            if (i != 0) {  
	                sb.append("-");  
	            }  
	            //mac[i] & 0xFF 鏄负浜嗘妸byte杞寲涓烘鏁存暟  
	            String s = Integer.toHexString(mac[i] & 0xFF);  
	            sb.append(s.length() == 1 ? 0 + s : s);  
	        }  
	        //鎶婂瓧绗︿覆鎵�鏈夊皬鍐欏瓧姣嶆敼涓哄ぇ鍐欐垚涓烘瑙勭殑mac鍦板潃骞惰繑鍥�  
	        macAddress = sb.toString().trim().toUpperCase();  
	        return macAddress;  
	    }  
	    //鑾峰彇闈炴湰鍦癐P鐨凪AC鍦板潃  
	    try {  
	        Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
	        InputStreamReader isr = new InputStreamReader(p.getInputStream());  
	        BufferedReader br = new BufferedReader(isr);  
	        while ((line = br.readLine()) != null) {  
	            if (line != null) {  
	                int index = line.indexOf(MAC_ADDRESS_PREFIX);  
	                if (index != -1) {  
	                    macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();  
	                }  
	            }  
	        }  
	        br.close();  
	    } catch (IOException e) {  
	        e.printStackTrace(System.out);  
	    }  
	    return macAddress;  
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(RedirectAttributes redirectAttributes) {
		// 娴ｈ法鏁ら弶鍐缁狅紕鎮婂銉ュ徔鏉╂稖顢戦悽銊﹀煕閻ㄥ嫰锟斤拷閸戠尨绱濈捄鍐插毉閻ц缍嶉敍宀�绮伴崙鐑樺絹缁�杞颁繆閹拷
		SecurityUtils.getSubject().logout();
//		redirectAttributes.addFlashAttribute("message", "閹劌鍑＄�瑰鍙忛柅锟介崙锟�");
		return "redirect:/login";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		return "/403";
	}

	// 妫ｆ牠銆�
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/home";
	}

	// 閸忣剙寰�
	@RequestMapping(value = "/enterprise", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/enterprise";
	}

	// 鐞涘奔绗�
	@RequestMapping(value = "/industry", method = RequestMethod.GET)
	public String homeRiskDetail(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/industry";
	}

	// 閸忓疇浠�
	@RequestMapping(value = "/relation", method = RequestMethod.GET)
	public String homerelation(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/relation";
	}
	// 閸忓疇浠�
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/upload";
	}
	//注册
	@RequestMapping(value = "/zhuce", method = RequestMethod.GET)
	public String zhuce(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/zhuce";
	}
	//评论
	@RequestMapping(value = "/pinglun", method = RequestMethod.GET)
	public String pinglun(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/pinglun";
	}
	@RequestMapping(value = "/jiankong", method = RequestMethod.GET)
	public String jiankong(HttpServletRequest request, Map<String, String> map, HttpServletResponse response,
			HttpSession session) {
		return "/user/jiankong";
	}

}