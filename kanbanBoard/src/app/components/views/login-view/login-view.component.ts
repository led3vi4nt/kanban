import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../../services/login.service';
import { handleValidationErrors } from '../../../utils/validation.handler';

@Component({
  selector: 'app-login-view',
  templateUrl: './login-view.component.html',
  styleUrls: ['./login-view.component.css']
})
export class LoginViewComponent implements OnInit {
    loginForm: FormGroup;

    constructor(private loginService: LoginService, private http: HttpClient, private router: Router) {
        this.loginForm = new FormGroup({
            'userName': new FormControl('', Validators.required),
            'password': new FormControl('', Validators.required),
        });
    }

    ngOnInit() {
    }

    onSubmit() {
        const data = {...this.loginForm.value};

        this.loginService.authenticate(data).subscribe(
            response => {
                let json = JSON.stringify(response);
                json  = json.replace(/[\u007F-\uFFFF]/g, function(chr) {
                    return "\\u" + ("0000" + chr.charCodeAt(0).toString(16)).substr(-4)
                });
                localStorage.setItem('user', json);
                this.router.navigateByUrl('/');
            },
            error => {
                error.error = {
                    fieldErrors: [
                        {
                            field: 'userName',
                            message: 'Invalid username or password',
                        },
                    ],
                };

                handleValidationErrors(error, this.loginForm);
            });

        return false;
    }

}
