import { Component, OnInit } from '@angular/core';
import { HomeServices } from './home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private homeService: HomeServices) {}

  ngOnInit(): void {
    this.fetchUserData();
  }

  fetchUserData() {
    this.homeService.fetchUser().subscribe((res: any) => {
      this.homeService.changeLoginStatus(),
        console.log(res, 'i am coming via decoded jwt');
    });
  }

  afterFetchingUser() {
    this.homeService.changeLoginStatus();
  }
}
