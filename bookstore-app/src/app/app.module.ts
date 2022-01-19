import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';

import { ListBookComponent } from './list-book/list-book.component';
import { BookService } from './book/book.service';
import { BookFormComponent } from './book-form/book-form.component';
import { DetailedBookComponent } from './detailed-book/detailed-book.component';

@NgModule({
  declarations: [
    AppComponent,
    ListBookComponent,
    BookFormComponent,
    DetailedBookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }