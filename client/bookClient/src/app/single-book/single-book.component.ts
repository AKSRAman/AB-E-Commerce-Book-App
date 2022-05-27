import { BookServices } from './../books/book.services';
import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';
import {Router ,ActivatedRoute} from '@angular/router';
@Component({
  selector: 'app-single-book',
  templateUrl: './single-book.component.html',
  styleUrls: ['./single-book.component.css']
})
export class SingleBookComponent implements OnInit {
  book:Book={
    addedOn: "",
    author: "",
    category: "",
    description: "",
    id: "",
    imageUrl: "https://rukminim2.flixcart.com/image/612/612/klmmrgw0/regionalbooks/f/3/g/the-power-of-your-subconscious-mind-original-imagypuj7qxyepyq.jpeg?q=70",
    pages: 1111,
    price: 1111,
    publishDate: "",
    rating: 0,
    title: "PSYCOLOGY OF MIND",
    updatedOn: "",
  };
 id:string|null="";
  constructor(private activatedRoute: ActivatedRoute, private router: Router, private bookData:BookServices) { }

  ngOnInit(): void {
    console.log("bobby")
    this.activatedRoute.paramMap.subscribe((params) => {
      this.id = params.get('id');
      this.getbook(this.id);
    });
  }
  getbook(id:string|null){
    this.bookData.getSingleBook(this.id).subscribe(res=>{
      console.log(res)
    this.book=res.book})
     
  }

  
    
  

   
}
