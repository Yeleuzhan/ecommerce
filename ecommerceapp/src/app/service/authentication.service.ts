import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private apiRegister = 'http://localhost:8080/api/v1/registration';
  private apiAuth = 'http://localhost:8080/api/v1/auth';

  constructor(private http: HttpClient) { }

  public register(registrationForm: User): Observable<string> {
    return this.http.post(`${this.apiRegister}`, registrationForm, {responseType: 'text'});
  }

  public activate(code: string): Observable<string> {
    return this.http.get(`${this.apiRegister}/activate/${code}`, {responseType: 'text'});
  }

  public login(loginForm: User): Observable<string> {
    return this.http.post(`${this.apiAuth}/login`, loginForm, {responseType: 'text'});
  }

  public sendCode(email: string): Observable<string> {
    return this.http.get(`${this.apiAuth}/forgot/${email}`, {responseType: 'text'});
  }

  public getEmailByCode(code: string): Observable<string> {
    return this.http.get(`${this.apiAuth}/reset/${code}`, {responseType: 'text'});
  }

  public resetPassword(resetPasswordForm: User): Observable<string> {
    return this.http.post(`${this.apiAuth}/reset`, resetPasswordForm, {responseType: 'text'});
  }

}
