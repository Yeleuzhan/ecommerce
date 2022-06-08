import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivateComponent } from './components/activate/activate.component';
import { SendCodeComponent } from './components/send-code/send-code.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { NewPasswordComponent } from './components/new-password/new-password.component';
import { Oauth2RedirectComponent } from './components/oauth2-redirect/oauth2-redirect.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'activate/:code', component: ActivateComponent },
  { path: 'login', component: LoginComponent },
  { path: 'forgot', component: SendCodeComponent },
  { path: 'reset/:code', component: NewPasswordComponent },
  { path: 'oauth2/redirect', component: Oauth2RedirectComponent },
  { path: '', redirectTo: 'registration', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
