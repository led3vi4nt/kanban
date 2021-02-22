import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStoryDetails } from '../models/user-story-details.model';
import { UserStoryStateUpdateCommand } from '../models/user-story-state-update-command.model';

const BASE_URL = 'http://localhost:8080/api/kanban';

@Injectable({
    providedIn: 'root',
})
export class UserStoryService {
    sprintId: number;

    constructor(private http: HttpClient) { }

    getAllUserStoriesOfSprint(sprintId: number): Observable<Array<UserStoryDetails>> {
        return this.http.get<Array<UserStoryDetails>>(BASE_URL + '/stories/' + sprintId);
    }

    postUserStory(data) {
        return this.http.post(BASE_URL, data);
    }

    updateStateOfUserStory(data: UserStoryStateUpdateCommand) {
        return this.http.post(BASE_URL + '/updateState', data);
    }

    deleteUserStory(id: number) {
        return this.http.post(BASE_URL + '/delete', id);
    }
}
