import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SprintDetails } from '../../../models/sprint-details.model';
import { RootService } from '../../../services/root.service';

@Component({
    selector: 'app-sprint-list',
    templateUrl: './sprint-list.component.html',
    styleUrls: ['./sprint-list.component.css'],
})
export class SprintListComponent implements OnInit {
    @Input() sprintList: Array<SprintDetails>;
    @Input() title: string;
    @Input() titleStyle: string;
    @Input() allowEdit: boolean;

    constructor(private root: RootService,
                private router: Router) { }

    ngOnInit() {
    }

    editSprint(id: number, event: MouseEvent) {
        event.stopPropagation();
        event.preventDefault();
        this.root.callerUrl = '';
        this.router.navigate(['/edit', id]);
    }
}
