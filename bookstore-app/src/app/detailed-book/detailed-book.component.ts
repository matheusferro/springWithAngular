import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../book/book';
import { BookService } from '../book/book.service';

@Component({
  selector: 'app-detailed-book',
  templateUrl: './detailed-book.component.html',
  styleUrls: ['./detailed-book.component.css']
})
export class DetailedBookComponent implements OnInit {

  book: Book;
  bookId: string="";


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bookService: BookService
  ) { 
    this.book = new Book();
  }

  ngOnInit(): void {
    console.log("wait. searching...")
    this.route.params.subscribe(params =>
      this.bookId = params['bookId']
    )
    this.bookService.findBookById(this.bookId).subscribe(bookFound => {
      this.book = bookFound
    })
  }

}
