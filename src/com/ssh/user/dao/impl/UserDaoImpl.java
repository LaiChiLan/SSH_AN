package com.ssh.user.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionSupport;
import com.ssh.user.dao.UserDao;
import com.ssh.user.model.User;
import com.ssh.util.*;
/**     
 * 缁鎮曠粔甯窗UserDaoImpl   
 * 缁粯寮挎潻甯窗閻劍鍩涢幒褍鍩梔ao鐎圭偟骞囬弫鐗堝祦鎼存挻鎼锋担婊勫复閸欙絽鐤勯悳锟� * 閸掓稑缂撴禍鐚寸窗anan   
 * 閸掓稑缂撻弮鍫曟？閿涳拷012-12-21 娑撳宕�1:07:43   
 * 娣囶喗鏁兼禍鐚寸窗anan  
 * 娣囶喗鏁奸弮鍫曟？閿涳拷012-12-21 娑撳宕�1:07:43   
 * 娣囶喗鏁兼径鍥ㄦ暈閿涳拷  
 * @version        
 * */
//鎶奤serDao瀹炰綋鍖�
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})

@Repository("userDao")
public class UserDaoImpl extends MyHibernateDaoSupport implements UserDao{
    private String HQL;
/*	 @Resource(name="sessionFactory") 
	 SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
*/
	public void addUser(User user)  {
	 	Session session =  this.getSession(false) ;
	//  	Transaction tc =  (Transaction)session.beginTransaction();
	 
		session.save(user);
		try {
		// 	tc.commit();
		} catch (Exception e) {
	 	//	tc.rollback();
			e.printStackTrace(); 
		} 
		session.close();
	}

	public void delUser(int userId) {
	 	Session session =  this.getSession(false) ;
		Transaction tc = (Transaction) session.beginTransaction();
		User u = new User(userId);
		session.delete(u);
		try {
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		session.close();
	}

	public void updateUser(User user) {
		Session session =  this.getSession(false) ;
		Transaction tc = (Transaction) session.beginTransaction();
		session.update(user);
		try {
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		
	}

	public List<User> selectUser()  {
		List<User> users = new ArrayList<User>();
		Session session =  this.getSession(false) ;
		Transaction tc = (Transaction) session.beginTransaction();
		List list = session.createQuery("From User").list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			User u = (User) iterator.next();
			users.add(u);
		}
		try {
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return users;
	}

	public User getUserByUserId(int userId)  {
		Session session =  this.getSession(false) ;
		Transaction tc = (Transaction) session.beginTransaction();
		//load 閺勵垵顕╅弰搴㈡殶閹诡喖绨辨稉顓濈鐎规艾鐡ㄩ崷銊ㄧ箹閺壜ゎ唶瑜版洩绱濆▽鈩冩箒閸掓瑦濮ら崙鐚寸窗ObjectNotFoundException
		//get 婵″倹鐏夐弻銉ょ瑝閸掓媽顔囪ぐ鏇礉鏉╂柨娲栭惃鍕Ц娑擄拷閲渘ull
		User user = (User)session.load(User.class, userId);
		try {
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return user;
	}

	public boolean isExitByName(String userName)  {
		Session session =  this.getSession(false) ;
		Transaction tc = (Transaction) session.beginTransaction();
		List user = (List)session.createQuery("From User u where u.userName=:userName").setString("userName", userName).list();
		if(user.size()>0){
			try {
				tc.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.close();
			return true;
		}
		try {
			tc.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return false;
	}

	public boolean isExitByNameAndPass(User user)  {
		System.out.println("testASAAAAAAAAAA");
		Session session =  this.getSession(false) ;
	 
		//Transaction tc = (Transaction) session.beginTransaction();
		HQL = "From User u where u.userName=:userName and u.passWord=:passWord" ;
		List users = (List)session.createQuery(HQL)
				.setString("userName", user.getUserName())
				.setString("passWord",user.getPassWord());
		//List users = (List)session.createQuery("From User u where u.userName=:userName and u.passWord=:passWord").setString("userName", user.getUserName()).setString("passWord", user.getPassWord()).list();
		
		if(users.size()>0){
			session.close();
			return true;
		}
	 
		session.close();
		return false;
	}

}
