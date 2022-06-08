import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public showLoading: boolean;

  constructor(private authenticationService: AuthenticationService) {}

  ngOnInit(): void {
    
  }

  public onLogin(user: User): void {
    this.showLoading = true;
    this.authenticationService.login(user).subscribe(
      (resp: string) => {
        this.showLoading = false;
        console.log(resp)
      },
      (err: HttpErrorResponse) => {
        this.showLoading = false;
        console.log(err.message);
      }
    );
  }

}
