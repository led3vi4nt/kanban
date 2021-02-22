import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class RootService {
    public sprintId: number;
    public callerUrl:string;

    constructor() { }
}
