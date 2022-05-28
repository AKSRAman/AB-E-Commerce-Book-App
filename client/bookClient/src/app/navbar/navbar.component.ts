import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

import { HomeServices } from '../home/home.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {

  timerId: number = 1000;

  constructor(private homeService: HomeServices) { }

  data: any;
  ngOnInit(): void {

  }
  loginStatus: boolean = this.homeService.loginStatus
}
