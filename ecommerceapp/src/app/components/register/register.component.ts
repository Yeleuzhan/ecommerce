import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  showLoading: boolean;

  constructor(private router: Router, 
    private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    
  }

  public onRegister(registrationForm: User): void {
    registrationForm.captcha = grecaptcha.getResponse();
    this.showLoading = true;
    this.authenticationService.register(registrationForm).subscribe(
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
