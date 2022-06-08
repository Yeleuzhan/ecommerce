import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-activate',
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.css']
})
export class ActivateComponent implements OnInit {

  public code: string;
  public message: string;

  constructor(private route: ActivatedRoute, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.code = this.route.snapshot.paramMap.get('code');
    this.authenticationService.activate(this.code).subscribe(
      (resp: string) => {
        console.log(resp)
      },
      (err: HttpErrorResponse) => {
        console.log(err.message);
      }
    );
  }

}
