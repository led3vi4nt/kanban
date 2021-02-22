import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SprintDetails } from '../../../models/sprint-details.model';
import { SprintExtDetails } from '../../../models/sprint-ext-details.model';
import { RootService } from '../../../services/root.service';
import { SprintService } from '../../../services/sprint.service';
import { UserStoryService } from '../../../services/user-story.service';

@Component({
    selector: 'app-sprint-form',
    templateUrl: './sprint-form.component.html',
    styleUrls: ['./sprint-form.component.css'],
})
export class SprintFormComponent implements OnInit {
    @Input() sprintToEdit: SprintExtDetails;
    @Output() cancel = new EventEmitter();
    @Output() bookmark = new EventEmitter();
    sprintStateOptions: Array<string> = [];
    showStoryForm: boolean;
    form: FormGroup = this.formBuilder.group({
        'badge': [null, [Validators.required, Validators.minLength(3), Validators.maxLength(16)]],
        'description': [null],
    });
    cancelLabel: string = 'CANCEL';

    constructor(private root: RootService,
                private formBuilder: FormBuilder,
                private sprintService: SprintService,
                private userStoryService: UserStoryService,
                private router: Router) { }

    ngOnInit() {
        this.initEditForm();
    }

    private initEditForm() {
        if (this.sprintToEdit) {
            this.cancelLabel = 'BACK';
            this.root.sprintId = this.sprintToEdit.id;
            this.form.controls.badge.setValue(this.sprintToEdit.badge);
            this.form.controls.description.setValue(this.sprintToEdit.description);
            this.form.addControl('sprintState', new FormControl(this.sprintToEdit.sprintState, []));
            this.form.addControl('id', new FormControl(this.sprintToEdit.id, []));
            this.sprintService.getSprintStateOptions().subscribe(
                options => this.sprintStateOptions = options,
                error => console.warn(error),
            );
        }
    }

    hideForm() {
        this.cancel.emit();
    }

    bookmarkSprint() {
        let sprintData: SprintDetails = this.form.value;
        this.sprintService.bookmarkSprint(sprintData).subscribe(
            () => this.bookmark.emit(),
            error => console.warn(error),
        );
    }

    switchUserStoryForm(to: boolean) {
        this.showStoryForm = to;
    }

    loadUserStories() {
        this.showStoryForm = false;
        this.userStoryService.getAllUserStoriesOfSprint(this.sprintToEdit.id).subscribe(
            stories => this.sprintToEdit.userStories = stories,
            error => console.warn(error),
        );
    }

    deleteSprint(id: number) {
        this.sprintService.deleteSprint(id).subscribe(
            () => this.router.navigate(['']),
            error => console.warn(error),
        );
    }

    deleteStory(id: number) {
        this.userStoryService.deleteUserStory(id).subscribe(
            () => this.loadUserStories(),
            error => console.warn(error),
        );
    }
}
