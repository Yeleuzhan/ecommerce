import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {

  private code = '';
  private email = '';

  constructor(private route: ActivatedRoute, private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('code');
    this.authenticationService.getEmailByCode(this.code).subscribe(
      (resp: string) => {
        this.email = resp;
      },
      (err: HttpErrorResponse) => {
        console.log(err.message);
      }
    );
  }

  public onNewPassword(newPasswordForm: User) {
    newPasswordForm.email = this.email;
    console.log(newPasswordForm);
    this.authenticationService.resetPassword(newPasswordForm).subscribe(
      (resp: string) => {
        console.log(resp);
      },
      (err: HttpErrorResponse) => {
        console.log(err.message);
      }
    );
    this.router.navigateByUrl("/login");
  }

}
