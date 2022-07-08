import { Component, Injectable, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDirective } from 'angular-bootstrap-md';
import { Constants } from 'src/app/constants';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})

export class MainComponent implements OnInit {

  @ViewChild('authenticationModal') authenticationModal!: ModalDirective;
  @ViewChild('forgotPasswordModal') forgotPasswordModal!: ModalDirective;
  @ViewChild('OTPModal') OTPModal!: ModalDirective;
  @ViewChild('changePasswordModal') changePasswordModal!: ModalDirective;

  username: string = '';
  password: string = '';
  email: string = '';
  OTP: string=''
  newPassword: string=''

  forgotPasswordMessage = ''
  errorMessage: string=''
  emailMessage: string=''
  passwordCheck: string=''
  OTPMessage: string=''
  changePasswordMessage:string=''

  usersArray: any = []
  usersArray2: any = []

  constructor(private router: Router, private userService: UserService) { }

 ngAfterViewInit(): void{
    this.authenticationModal.show();
  }

  ngOnInit() {
    this.findAllUsers()
    if(Constants.CHECK_LOGIN){
      this.router.navigate(['/users'])
    }
 }
  showModal(): void {
    if(!Constants.CHECK_LOGIN){
      this.authenticationModal.show();
    }
  } 

  login(): void{
    this.errorMessage = ''

    for(let user of this.usersArray)
    {    
      if(this.username === user.username)
      {
        Constants.CHECK_LOGIN = true
        break
      }
    }

    if(Constants.CHECK_LOGIN === true)
    {
      this.passwordCheker()
    }
    else
    {
      this.errorMessage = 'Username / Password is incorrect'
    }
  }

  clearFormData(): void{
    this.errorMessage = "";
    this.password = "";
  }

  public findAllUsers(): void{
    //clear existing array
    this.usersArray = [];

    //call findAllUsers API from userService
    this.userService.findAllUsers()
    .subscribe(data => {
      for(let user of data){
        this.usersArray.push(user)
      }
    }  
    )
  }

  public async passwordCheker(): Promise<void>{
    Constants.CHECK_LOGIN = false

    let user = {
      username: this.username,
      password: this.password
    } 

    this.usersArray2 = []

    this.userService.passwordChecker(user).subscribe(users =>{
      this.usersArray2 = []
      this.usersArray2.push(users) 
      if(this.usersArray2[0].username === this.username){
        Constants.CHECK_LOGIN = true
        this.router.navigate(['/users'])
      }
      else{
        this.errorMessage = 'Username / Password is incorrect'
      }
    },
    error =>{
      console.log(error)
      this.errorMessage = error
    })
  }

  showForgotPasswordModal(): void{
    this.forgotPasswordModal.show()
    this.authenticationModal.hide()
  }
  
  forgotPassword(): void{
    let user = {
      name: "Auto Generated Mail",
      email: this.email,
      subject: "Password Reset",
      message: "0"  
    }

    this.userService.forgotPassword(user).subscribe(data =>{
        if(data.code === 0){
          this.OTPMessage = 'Email sent successfully'
          this.forgotPasswordModal.hide()
          this.OTPModal.show()
       }
       else{
        this.emailMessage = 'No such email exists in our database'
       }
    })
  }

  checkOTP(): void{
    let user = {
      email: this.email,
      otp: this.OTP
    }

    this.userService.checkOTP(user).subscribe(data =>{
      if(data.code === 0){
        this.changePasswordMessage = 'Otp is correct'
        this.OTPModal.hide()
        this.changePasswordModal.show()
      }
      else if(data.code === 1){
        this.OTPMessage = 'OTP is not correct'
      }
    })
  }

  newPasswordChange(): void{ 
      let user = {
        email: this.email,
        password: this.newPassword.length !==0 ? this.newPassword:null
      }

      if(this.newPassword.length === 0){
        this.changePasswordMessage = 'Password cannot be null'
        return;
      }
      
      this.userService.updateUser(user).subscribe(data =>{},)

      this.changePasswordModal.hide(); 
      this.authenticationModal.show();
      this.errorMessage = 'Password Changed Successfully'
  }
}

