package my.car;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import boundry.MemberFacade;
import entities.Member;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//import javax.persistence.EntityManager;

/**
 *
 * @author DELL
 */
@ManagedBean(name="UserView")
@SessionScoped
public class UserView {
   // private EntityManager em;
    @EJB
    private MemberFacade memberFacade;
    private Member member;
    public String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int id;
    private String password;
    @ManagedProperty(value="#{MemberView}")
    private MemberView memberView;

    public MemberView getMemberView() {
        return memberView;
    }

    public void setMemberView(MemberView memberView) {
        this.memberView = memberView;
    }
    public MemberFacade getMemberFacade() {
        return memberFacade;
    }

    public void setMemberFacade(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    private void addMessage(FacesMessage message){
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public String validateUser(){
        
        System.out.println("email"+email);
        List<String> list=memberFacade.validateUser(email);
         //String pwd=memberFacade.validateUser(userId);
         Iterator iterator = list.iterator();
       if(iterator.hasNext())
       {
        // System.out.println("Size of list="+list.size());
        // if(list.size()>0)
        //{
            member=(Member)iterator.next();
           System.out.println("inside first ifffff");
          // String pwd= list.get(0);
           String pwd=member.getPassword();
           String firstname=member.getFirstname();
           System.out.println("firstname"+firstname+"password"+pwd);
               if(pwd.equals(password))
                     {
                       System.out.println("inside second if"+email);
                       //this.member.setFirstname(firstname);
                       this.memberView.setMember(member);
                       System.out.println("After setting ....");
                       return "Mypage";
                     }
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentication Failed.Try Login Again!!!", null));       
        return "Login";
        } 
        else
        {
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Authentication Failed.Try Login Again!!!", null));       
        return "Login";
        }
    }
    public UserView() {
    }
}
