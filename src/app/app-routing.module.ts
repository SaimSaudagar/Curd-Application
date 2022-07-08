import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { AuthenticationGuard } from './guards/authentication.guard';
import { MainComponent } from './components/main/main.component';

const routes: Routes = [
  { path: 'users', component: UserComponent, canActivate: [AuthenticationGuard] },
  { path: 'main', component: MainComponent },
  { path: '**', redirectTo: '/main' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
