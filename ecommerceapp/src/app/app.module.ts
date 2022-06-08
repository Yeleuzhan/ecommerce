import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule } from '@angular/forms';
import { RecaptchaModule } from 'ng-recaptcha';
import { ActivateComponent } from './components/activate/activate.component';
import { LoginComponent } from './components/login/login.component';
import { SendCodeComponent } from './components/send-code/send-code.component';
import { NewPasswordComponent } from './components/new-password/new-password.component';
import { Oauth2RedirectComponent } from './components/oauth2-redirect/oauth2-redirect.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    ActivateComponent,
    LoginComponent,
    SendCodeComponent,
    NewPasswordComponent,
    Oauth2RedirectComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RecaptchaModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
