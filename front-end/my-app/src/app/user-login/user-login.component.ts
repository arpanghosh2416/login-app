import { Component, OnInit } from '@angular/core';

import { User } from '../user';
import { LoginuserService } from '../loginuser.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})

export class UserLoginComponent implements OnInit {

  user: User = new User();
  showMessage: boolean = false;
  showWrongMessage: boolean = false;
  showSuccessMessage: boolean = false;

  constructor(private loginuserservice: LoginuserService) {
  }

  ngOnInit(): void {
  }

  closeMessage(): void {
    this.showMessage = false;
  }

  closeWrongMessage(): void {
    this.showWrongMessage = false;
  }

  closeSuccessMessage(): void {
    this.showSuccessMessage = false;
  }

  userLogin(): void {
    console.log(this.user);
    if ((this.user.username === '' || this.user.password === '') || this.user.username === null || this.user.password === null)
      this.showMessage = true;
    else {
      this.loginuserservice.loginUser(this.user).subscribe(
        response => {
          this.showSuccessMessage = true;
          console.log('server:', response);
        },
        error => {
          this.showWrongMessage = true;
          console.log('server:', error);
        }
      );
      console.log('Login Done');
    }
  }

}