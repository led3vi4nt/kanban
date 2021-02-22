import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SprintDetails } from '../models/sprint-details.model';
import { SprintExtDetails } from '../models/sprint-ext-details.model';

const BASE_URL = 'http://localhost:8080/api/kanban';

@Injectable({
    providedIn: 'root',
})
export class SprintService {

    constructor(private http: HttpClient) { }

    getSprintStateOptions(): Observable<Array<string>> {
        return this.http.get<Array<string>>(BASE_URL + '/sprints/states');
    }

    getAllSprint(): Observable<Array<SprintDetails>> {
        return this.http.get<Array<SprintDetails>>(BASE_URL);
    }

    getSprintDetails(id: number): Observable<SprintExtDetails> {
        return this.http.get<SprintExtDetails>(BASE_URL + '/sprints/' + id);
    }

    deleteSprint(id: number) {
        return this.http.post(BASE_URL + '/sprints/delete', id);
    }

    bookmarkSprint(data) {
        return this.http.post(BASE_URL + '/sprints', data);
    }
}
