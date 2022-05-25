import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HostListener } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  // @HostListener('window:scroll', ['$event'])
  
  // onWindowScroll() {
  //     let element = document.querySelector('.navbar2') as HTMLElement;
  //     if (window.pageYOffset > element.clientHeight) {
  //       element.classList.add('navbar');
  //     } else {
  //       element.classList.add('navbar2');
  //     }
  //   }

}
