import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import Swal from 'sweetalert2';

import { HomeServices } from '../home/home.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {

  timerId: number = 1000;

  constructor(private homeService: HomeServices, private http: HttpClient) { }

  isLogin: boolean = false

  ngOnInit(): void {
    this.fetchUserData()
  }

  fetchUserData() {
    let token: any = localStorage.getItem("jwtToken");
    token = JSON.parse(token);
    if (!token) {
      return console.log("token is not present checked by navbar")
    }
    this.homeService.fetchUser().subscribe((res: any) => {
      this.isLogin = true
      console.log(res, 'i am coming via decoded jwt');
    });
  }

  logOut() {
    localStorage.removeItem("jwtToken");
    window.location.reload()
  }

}
