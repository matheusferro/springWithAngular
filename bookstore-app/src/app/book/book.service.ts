import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Book } from './book';
import { SaveBook } from './book';
import { Observable } from 'rxjs-compat/Observable';

@Injectable()
export class BookService {

  private booksUrl: string;

  constructor(private http: HttpClient) {
    this.booksUrl = 'http://localhost:8080/books';
  }

  public findAll(): Observable<Book[]> {
    return this.http.get<Book[]>(this.booksUrl);
  }

  public save(book: SaveBook) {
    return this.http.post<SaveBook>(this.booksUrl, book);
  }
}
