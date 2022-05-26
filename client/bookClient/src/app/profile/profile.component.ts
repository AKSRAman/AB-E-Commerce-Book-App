import { Component, OnInit } from '@angular/core';
import { HomeServices } from '../home/home.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  id: string = ""
  name: string = "";
  email: string = ""
  mobile: number = 0
  address: string = ""

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
  }

}
