import { Component, OnInit } from '@angular/core';
import { Book } from '../book.model';

@Component({
  selector: 'app-single-book',
  templateUrl: './single-book.component.html',
  styleUrls: ['./single-book.component.css']
})
export class SingleBookComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
book:Book={
  addedOn: "",
  author: "bobby",
  category: "",
  description: "some Description",
  id: "",
  imageUrl: "https://rukminim2.flixcart.com/image/612/612/klmmrgw0/regionalbooks/f/3/g/the-power-of-your-subconscious-mind-original-imagypuj7qxyepyq.jpeg?q=70",
  pages: 999,
  price: 5555,
  publishDate: "",
  rating: 0,
  title: "Psycology",
  updatedOn: "",
};

}
