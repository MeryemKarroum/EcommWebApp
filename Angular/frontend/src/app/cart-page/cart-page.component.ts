import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {CartItem} from '../models/cartItem.model';
import {CartService} from '../service/cart.service';
import {AsyncPipe, CurrencyPipe, NgForOf, NgIf} from '@angular/common';
import {Router, RouterLink} from '@angular/router';
import {AuthorizationService} from '../service/authorization.service';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    CurrencyPipe,
    RouterLink
  ],
  templateUrl: './cart-page.component.html',
  styleUrl: './cart-page.component.css'
})

export class CartPageComponent implements OnInit {
  cartItems$!: Observable<CartItem[]>;

  constructor(
    private cartService: CartService,
    private authorizationService: AuthorizationService,
    private router: Router) {}

  ngOnInit(): void {
    this.checkClientRole();
    this.cartItems$ = this.cartService.cart$;
  }
  checkClientRole() {
    if (!this.authorizationService.hasRole('CLIENT')) {
      console.error('Access denied');
      this.router.navigate(['/access-denied']);
    }
  }
  removeFromCart(itemId: number): void {
    this.cartService.removeFromCart(itemId);
  }

  updateQuantity(itemId: number, quantity: number): void {
    this.cartService.updateQuantity(itemId, quantity);
  }

  getCartTotal(): number {
    return this.cartService.getCartTotal();
  }

  checkout(): void {
    // Implement checkout logic here
    console.log('Checkout');
    this.cartService.clearCart();
  }
}
