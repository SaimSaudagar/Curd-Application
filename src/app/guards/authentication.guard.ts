import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { Constants } from '../constants';
import { MainComponent } from '../components/main/main.component';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(localStorage.getItem('check'))
    {
      Constants.CHECK_LOGIN = true
    }
    if(Constants.CHECK_LOGIN == true){
      //user is authenticated
      return true;
    }
  
    // user is not authenticated => redirect to login page
    this.router.navigate(['/main'], { queryParams: {returnUrl: state.url}});
    return false;
  }
}
