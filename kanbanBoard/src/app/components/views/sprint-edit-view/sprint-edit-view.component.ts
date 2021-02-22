import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SprintDetails } from '../../../models/sprint-details.model';
import { SprintExtDetails } from '../../../models/sprint-ext-details.model';
import { RootService } from '../../../services/root.service';
import { SprintService } from '../../../services/sprint.service';

@Component({
    selector: 'app-sprint-edit',
    templateUrl: './sprint-edit-view.component.html',
    styleUrls: ['./sprint-edit-view.component.css'],
})
export class SprintEditViewComponent implements OnInit, OnDestroy {
    sprint: SprintExtDetails;

    constructor(private root: RootService,
                private router: Router,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder,
                private sprintService: SprintService) {
        this.route.paramMap.subscribe(
            paramMap => {
                let sid = +paramMap.get('id');
                if (sid) {
                    sprintService.getSprintDetails(sid).subscribe(
                        sprint => this.sprint = sprint,
                        error => console.warn(error),
                    );
                }
            },
            error => console.warn(error),
        );
    }

    ngOnInit() {
        if (!localStorage.getItem('user')) {
            this.router.navigate(['/login'])
        }
    }


    ngOnDestroy(): void {
        this.root.callerUrl = 'edit/' + this.sprint.id;
    }

    navigateToPreviousView() {
        this.router.navigate([this.root.callerUrl]);
    }
}
