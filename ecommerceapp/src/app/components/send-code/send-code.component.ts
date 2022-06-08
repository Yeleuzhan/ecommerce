import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './send-code.component.html',
  styleUrls: ['./send-code.component.css']
})
export class SendCodeComponent implements OnInit {

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    
  }

  public onForgotPassword(forgotPasswordForm: User): void {
    this.authenticationService.sendCode(forgotPasswordForm.email).subscribe(
      (resp: string) => {
        console.log(resp)
      },
      (err: HttpErrorResponse) => {
        console.log(err.message);
      }
    );
    this.router.navigateByUrl('/login');
  }

}
