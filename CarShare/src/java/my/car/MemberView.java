/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.car;

import boundry.AddressFacade;
import boundry.MemberFacade;
import entities.Address;
import entities.Member;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
//import javax.faces.validator.ValidatorException;

/**
 *
 * @author DELL
 */
@ManagedBean(name="MemberView")
@SessionScoped
public class MemberView implements Serializable{
    
     private Member member;
    private Address address;
     public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    private String confirmpassword; 

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    
    @EJB
    private AddressFacade addressFacade;
  
    public AddressFacade getAddressFacade() {
        return addressFacade;
    }

    public void setAddressFacade(AddressFacade addressFacade) {
        this.addressFacade = addressFacade;
    }

   
    
   

   
    
    @EJB
    private MemberFacade memberFacade;
    
    public MemberView(){
        this.member = new Member();
        if(this.member.getAddress() == null) {
            this.member.setAddress(new Address());
        }
        //this.address = new Address();
    } 
    //TODO remove this - this is only for testing reservation!!!! Merca
   /* @PostConstruct
    public void init(){
        this.member = memberFacade.getById((long)51);
    }*/
    
    
    public MemberFacade getMemberFacade() {
        return memberFacade;
    }

    public void setMemberFacade(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }

    private void addMessage(FacesMessage message){
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public String addUser(){
        System.out.println("Inside addUser.....");
      this.memberFacade.create(member);
      //this.addressFacade.create(address);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "User Registration Successful!!!", null));
        return "RegConfirm";
    }
    public String updateUser(){
        System.out.println("Inside updateUser.....");
      this.memberFacade.edit(member);
      //this.addressFacade.edit(address);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "User Updation Successful!!!", null));
        return "Mypage";
    }
    public String userExist()
    {
        String user=member.getFirstname();
        if(user!=null)
        {
            return "Mypage";
        }
        else
        {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "User needs to login!!!", null));
            return "Login";
        }
        
    }
    
    public void pwdValidator(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
         
         //Get the first password from the attribute.
        UIInput passwordComponent = (UIInput) component.getAttributes().get("password");
 
        //Get the value from the UIInput component.
        String passwordc = (String) passwordComponent.getValue();
 
        //Get the value entered in the second string from the component parameter passed to the method.
        String confirm = (String) value;
 
        //Compare both fields.
        if(!passwordc.equals(confirm)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords dont match!!!", null));
        }
 
    }
    
    /**
     *
     * @param context
     * @param component
     * @param value
     */
   /* public void emailValidator(FacesContext context, UIComponent component, Object value){
    //Get the first email address from the attribute
    UIInput emailComponent = (UIInput) component.getAttributes().get("email");
 
    //Retrieve the String value from the component
    String emails = (String) emailComponent.getValue();
 
    //Convert the value parameter to a String.
    String confirmEmail = (String) value;
 
    if(emails.indexOf("@") < 0 || emails.indexOf("@") == emails.length()-1){
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Email Address Please use eg. user@domain", null));
    }else if(!emails.equals(confirmEmail)){
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Addresses don't match", null));
        addressesToAdd.add(address);
        this.member.setAddresses(addressesToAdd);
        this.memberFacade.create(member);
        return "index";
    }
}*/
    
    
  

  
    
   
}
