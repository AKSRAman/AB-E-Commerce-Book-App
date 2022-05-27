import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HostListener } from '@angular/core';
import { fromEvent, Observable } from 'rxjs';
import { ajax } from 'rxjs/ajax';
//import { debounceTime, distinctUntilChanged, filter, map, switchMap } from 'rxjs/operators';
import { HomeServices } from '../home/home.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  timerId: number = 1000;

  constructor(private homeService: HomeServices) { }

   data:any;
  ngOnInit(): void {
//     const searchBox = document.getElementById('searchBox') as HTMLInputElement;
// const typeahead = fromEvent(searchBox, 'input').pipe(
//   map(e => (e.target as HTMLInputElement).value),
//   filter(text => text.length > 3),
//   debounceTime(1000),
//   distinctUntilChanged(),
//   switchMap(searchTerm => ajax(`http://localhost:8080/books/search?=${searchTerm}`))
// );
// typeahead.subscribe(data => {
//  this.data=data;
//  console.log(data);
// });
  }
  
  loginStatus: boolean = this.homeService.loginStatus

  // @HostListener('window:scroll', ['$event'])

  // onWindowScroll() {
  //     let element = document.querySelector('.navbar2') as HTMLElement;
  //     if (window.pageYOffset > element.clientHeight) {
  //       element.classList.add('navbar');
  //     } else {
  //       element.classList.add('navbar2');
  //     }
  //   }
  //   searchproduct() {
  //    (`http://localhost:2000/api/Searchproducts`);
  //     let data = await res.json();
  //     return data;
  // }

  //      main() {
  //      const name = 
  //     if (name.length < 3) {
  //         return false;
  //     }



  //     let product_data = res;
  //     var search_data = [];
  //     product_data.forEach((el) => {
  //         if (el.name.includes(name)) {
  //             console.log(el);
  //             search_data.push(el);
  //         }
  //     });
  //     //appendproduct(search_data);
  // }
  //     debounce(func:Function, delay:number) {

  //     if (this.timerId) {
  //         clearTimeout(this.timerId)
  //     }

  //     this.timerId = setTimeout(function () {
  //         func();
  //     }, delay);
  // }


}
