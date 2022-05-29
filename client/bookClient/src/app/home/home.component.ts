import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { HomeServices } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private homeService: HomeServices) { }

  ngOnInit(): void {
    this.fetchUserData();
  }

  fetchUserData() {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    if (!token) {
      return console.log("token is not present checked by home")
    }
    this.homeService.fetchUser().subscribe((res: any) => {
        console.log(res, 'i am coming via decoded jwt');
    });
  }

}
