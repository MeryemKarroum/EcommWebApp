import { Component } from '@angular/core';
import {CurrencyPipe, DatePipe, NgForOf} from '@angular/common';

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [
    CurrencyPipe,
    DatePipe,
    NgForOf
  ],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent {
  orders = [
    { id: 1, user: 'User 1', total: 59.97, date: new Date('2023-05-01') },
    { id: 2, user: 'User 2', total: 89.98, date: new Date('2023-05-02') },
    { id: 3, user: 'User 3', total: 39.99, date: new Date('2023-05-03') },
  ];

  constructor() { }

  ngOnInit(): void {
    // In the future, we'll fetch real data here
  }
}
