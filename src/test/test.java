package test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.ssh.user.dao.UserDao;
import com.ssh.user.dao.impl.UserDaoImpl;
import com.ssh.user.model.User;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})

/*@Controller */
public class test extends ActionSupport {
   
  //  @Resource(name="userDao")
	  @Autowired
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	  @Test
	public void test() {
	      User user = new User();
	      user.setUserName("a");
	      user.setPassWord("b");
	       //    UserDao userDao = new UserDaoImpl();
	        
		System.out.println("hihihih");
		
	    userDao.addUser(user);
		        
		 boolean flag = userDao.isExitByNameAndPass(user);

	}

}
