import {Component, OnInit} from '@angular/core';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
  users = [
    { id: 1, name: 'User 1', email: 'user1@example.com', role: 'Customer' },
    { id: 2, name: 'User 2', email: 'user2@example.com', role: 'Admin' },
    { id: 3, name: 'User 3', email: 'user3@example.com', role: 'Customer' },
  ];

  constructor() { }

  ngOnInit(): void {
    // In the future, we'll fetch real data here
  }

  deleteUser(id: number) {
    // Implement delete logic here
    console.log(`Deleting user with id ${id}`);
  }
}
