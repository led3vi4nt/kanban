import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SprintDetails } from '../../../models/sprint-details.model';
import { LoginService } from '../../../services/login.service';
import { RootService } from '../../../services/root.service';
import { SprintService } from '../../../services/sprint.service';

@Component({
    selector: 'app-sprint-view',
    templateUrl: './sprint-view.component.html',
    styleUrls: ['./sprint-view.component.css'],
})
export class SprintViewComponent implements OnInit {
    activeSprints: Array<SprintDetails>;
    sprintsDone: Array<SprintDetails>;
    showSprintForm: boolean = false;

    constructor(private root: RootService,
                private router: Router,
                private loginService: LoginService,
                private sprintService: SprintService) { }

    ngOnInit() {
        this.loadSprints();
    }

    processSprintList(sprintList: Array<SprintDetails>) {
        for (let sprint of sprintList) {
            if (sprint.sprintState == 'All Done') {
                this.sprintsDone.push(sprint);
            } else {
                this.activeSprints.push(sprint);
            }
        }
    }

    loadSprints() {
        this.activeSprints = [];
        this.sprintsDone = [];
        this.sprintService.getAllSprint().subscribe(
            sprintList => this.processSprintList(sprintList),
            error => console.warn(error),
        );
        this.showSprintForm = false;
    }

}
