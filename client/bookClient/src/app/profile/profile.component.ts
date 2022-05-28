import { Component, OnInit } from '@angular/core';
import { HomeServices } from '../home/home.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {
  editMode:boolean=true;
  typeInput="password";
  change="visibility_off"
  type=false;
  id: string = ""
  name: string = ""
  email: string = ""
  mobile: number = 0
  address: string = ""
  password:string="";
   
  tempUser={
    id: "",
    fullName: "",
    email:"",
    mobNumber:  0,
    address: "",
    password:"",
  }
  constructor(private homeService: HomeServices) { }

  ngOnInit(): void {
    this.fetchUserData()
  }

  fetchUserData() {
    this.homeService.fetchUser().subscribe((res: any) => {
      this.assignValue(res);
    });
  }

  assignValue(obj: any) {
    console.log(obj.user, "profile")
    this.id = obj.user.id
    this.name = obj.user.fullName;
    this.email = obj.user.email;
    this.mobile = obj.user.mobNumber;
    this.address = obj.user.address;
    this.password=obj.user.password;
  }
   setEditMode(mode:boolean) {
     this.editMode=mode;
     if(mode==true){
       this.tempUser.id=this.id;
       this.tempUser.address=this.address;
       this.tempUser.email=this.email;
       this.tempUser.mobNumber=this.mobile;
       this.tempUser.password=this.password;
       this.tempUser.fullName=this.name;
     }

   }
   updateUser(){
    this.homeService.updateUser(this.tempUser).subscribe((res) => {
      this.fetchUserData()
      this.editMode = false;
      alert(`The Book ${this.tempUser.fullName} with id ${this.tempUser.id} updated successfuly`);
    });;
 
   }

   changeType(type:string){
     if(type=="password"){
      this.typeInput="text"
      this.change="visibility"
      
    }
   else{ 
      this.typeInput="password"
      this.change="visibility_off"
   }
    
   }


}
