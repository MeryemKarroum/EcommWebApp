import { Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
import {ProductListComponent} from './product-list/product-list.component';
import {CartPageComponent} from './cart-page/cart-page.component';
import {AdminDashboardComponent} from './admin/admin-dashboard/admin-dashboard.component';
import {ProductFormComponent} from './admin/product-form/product-form.component';
import {ProductAdminlistComponent} from './admin/product-adminlist/product-adminlist.component';
import {UserListComponent} from './admin/user-list/user-list.component';
import {OrderListComponent} from './admin/order-list/order-list.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AccessDeniedComponent} from './access-denied/access-denied.component';


export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'shop', component: ProductListComponent  },
  { path: 'products/:categoryId', component: ProductListComponent },
  { path: 'cart', component: CartPageComponent },
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'admin/products/new', component: ProductFormComponent },
  { path: 'admin/products', component: ProductAdminlistComponent },
  { path: 'admin/users', component: UserListComponent },
  { path: 'admin/orders', component: OrderListComponent },
  { path: 'login', component : LoginComponent},
  { path: 'register', component : RegisterComponent},
  { path: 'access-denied', component : AccessDeniedComponent}
];


