import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Constants } from 'src/app/constants';
import { ModalDirective } from 'angular-bootstrap-md';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

//common elements
mainMessage: string = ''
filterInPlace: boolean = false

//find all users
usersArray: any = [];
usersTable: any = ['ID', 'Username', 'Password', 'First Name', 'Last Name', 'Age', 'Email', 'Phone Num', '']
  
  //add new user
  @ViewChild("addNewUserModal") addNewUserModal!: ModalDirective
  
  addNewUserInfoMessage: String = ''

  firstName: String = ''
  lastName: String = ''
  username: String = ''
  password: String = ''
  age: number = 0
  email: String = ''
  phoneNum: String = ''
  
  //Find user by id
  @ViewChild("findUserByIdModal") findUserByIdModal!: ModalDirective

  id: number = 0;

  findUserByIdMessage: String = ''
  constructor(private userService: UserService, private router: Router) { }

  //Find by criteria
  @ViewChild("findByCriteriaModal") findByCriteriaModal!: ModalDirective

  findByCriteriaMessage: string = ''
  criteria: string = ''
  searchItem: string = ''
  criteriaArray: any = ['username', 'firstName', 'lastName', 'age', 'email', 'phoneNum']
  
  //Detele User
  @ViewChild("deleteUserModal") deteleUserModal!: ModalDirective

  //Update User
  @ViewChild("updateUserModal") updateUserModal!: ModalDirective
  updatedUserMessage: string = ''

  ngOnInit(): void {
    localStorage.setItem('check', 'true')
    this.mainMessage = 'Login Successful'
    this.findAllUsers();
  }

  clearData(): void{
    //Meassages
    this.mainMessage = ''
    this.addNewUserInfoMessage = ''
    this.findUserByIdMessage = ''
    this.findByCriteriaMessage = ''
    this.updatedUserMessage = ''

    //Fields
    this.id = 0
    this.firstName = ''
    this.lastName = ''
    this.username = ''
    this.password = ''
    this.age = 0
    this.email = ''
    this.phoneNum = ''
    this.criteria = ''
    this.searchItem = ''
  }

  public findAllUsers(): void{
    //clear existing array
    this.usersArray = [];
    
    //call findAllUsers API from userService
    this.userService.findAllUsers()
    .subscribe(data => {
      for(let user of data){
        this.usersArray.push(user);
      }
      //this.mainMessage = this.usersArray.length.toString()
    },
    error =>{
      this.mainMessage = error
    }
    )
    //this.mainMessage = this.usersArray.length.toString()
  }

  addNewUser(): void{
    if(this.firstName.length === 0 || this.lastName.length === 0 || this.username.length === 0 || this.password.length === 0 || this.age === 0 || this.email.length === 0 || this.phoneNum.length === 0){
      this.addNewUserInfoMessage = Constants.MANDATORY_FIELDS_ERROR_MESSAGE
        return
    } 
    
    let user = {
      firstName: this.firstName,
      lastName: this.lastName,
      username: this.username,
      password: this.password,
      age: this.age,
      email: this.email,
      phoneNum: this.phoneNum
    }

    this.userService.addNewUser(user)
    .subscribe(data => {
      this.addNewUserModal.hide()
      this.mainMessage = "New user has been added"
      this.findAllUsers()
    },
    error =>{
      console.log(error)
      this.addNewUserInfoMessage = error
    })
  }

  findUserById(): void{
    if(isNaN(this.id)){
      this.findUserByIdMessage = Constants.NOT_A_NUMBER_ERROR_MESSAGE
      return
    }

    this.userService.findUserById(this.id)
    .subscribe(user =>{
      // clear existing array of users
      this.usersArray = []
      this.usersArray.push(user)

      //filtered data
      this.filterInPlace = true
      this.findUserByIdModal.hide();
    },
    error =>{
      if(error.status === 404){
        this.findUserByIdMessage = Constants.USER_NOT_FOUND
      }else{
        this.findUserByIdMessage = error.message
      }
    })
  }

  findByCriteria():void{
    this.criteria = (<HTMLSelectElement>document.getElementById('criteria')).value
    
    if(this.criteria.length === 0 || this.searchItem.length === 0){
      this.findByCriteriaMessage = Constants.MANDATORY_FIELDS_ERROR_MESSAGE
      return
    }

    if(this.criteria === 'age' && isNaN(Number(this.searchItem))){
      this.findByCriteriaMessage = Constants.NOT_A_NUMBER_ERROR_MESSAGE
      return
    }

    this.userService.findByCriteria(this.criteria, this.searchItem)
    .subscribe(users =>{
      // clear the arrayof users
      this.usersArray = []

      for(let user of users){
        this.usersArray.push(user)
      }

      this.findByCriteriaModal.hide()
      //filtered data
      this.filterInPlace = true
    },
    error =>{
      if(error.status === 404){
        this.findByCriteriaMessage = Constants.USER_NOT_FOUND_FOR_CRITERIA_ERROR_MEASSAGE
      }else{
        this.findByCriteriaMessage = error
      }
    }
    )
  }

  openUpdateUserModal(user: any): void{
    this.clearData();

    this.id = user.id
    this.firstName = user.firstName
    this.lastName = user.lastName
    this.username = user.username
    this.password = ''
    this.age = user.age
    this.email = user.email
    this.phoneNum = user.phoneNum

    this.updateUserModal.show();
  }

  updateUser(): void{
    let user = {
      id: this.id,
      firstName: this.firstName.length !==0 ? this.firstName:null,
      lastName: this.lastName.length !==0 ? this.lastName:null,
      username: this.username.length !==0 ? this.username:null,
      password: this.password.length !==0 ? this.password:null,
      age: this.age !== undefined ? this.age:null,
      email: this.email.length !== 0 ? this.email:null,
      phoneNum: this.phoneNum.length !==0 ? this.phoneNum: null
    }

    this.userService.updateUser(user).subscribe(data =>{
      this.updateUserModal.hide();
      this.mainMessage = Constants.UPDATED_USER_INFO_MESSAGE
      this.findAllUsers()
      this.filterInPlace = false
    },
    error =>{
      this.updatedUserMessage = error
    }
    )
  }

  openDeleteUserModal(user: any): void{
    this.clearData();

    this.id = user.id

    this.deteleUserModal.show()
  }
  
  deleteUser(): void{
    this.userService.deleteUser(this.id)
    .subscribe(data =>{
      this.deteleUserModal.hide();
      this.mainMessage = `User with id ${this.id} has been deleted`
      this.findAllUsers();
      this.filterInPlace = false;
    },
    error =>{
      this.mainMessage=error
    })
  }

  resetFilter(): void{
    this.filterInPlace = false
    this.findAllUsers();
  }

  logout(): void{
    localStorage.removeItem('check')
    Constants.CHECK_LOGIN = false
    this.router.navigate(['/main'])
  }
}
