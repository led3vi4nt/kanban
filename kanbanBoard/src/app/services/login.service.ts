import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';


const BASE_URL ='http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class LoginService {


    constructor(private http: HttpClient, private router: Router) {}

    authenticate(credentials): Observable<any> {

        const headers = new HttpHeaders(credentials ? {
            authorization: 'Basic ' + btoa(credentials.userName + ':' + credentials.password),
        } : {});

        return this.http.get(BASE_URL + '/api/kanban/auth', {headers: headers});
    }

    logout() {
        this.http.post(BASE_URL + '/logout', {}).subscribe(() => {
            localStorage.removeItem('user');
            this.router.navigateByUrl('/login');
        });
    }
}
