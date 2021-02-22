import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RootService } from '../../../services/root.service';
import { UserStoryService } from '../../../services/user-story.service';

@Component({
    selector: 'app-user-story-form',
    templateUrl: './user-story-form.component.html',
    styleUrls: ['./user-story-form.component.css'],
})
export class UserStoryFormComponent implements OnInit {
    @Output() newUserStory = new EventEmitter();
    @Output() close = new EventEmitter();

    @Input() nextStoryId: number;

    form: FormGroup = this.formBuilder.group({
        'sprintId': [null, [Validators.required]],
        'title': [null],
        'description': [null, [Validators.required]],
    });

    constructor(private formBuilder: FormBuilder,
                private root: RootService,
                private router: Router,
                private userStoryService: UserStoryService) { }
    @ViewChild('description', {static: true}) descriptionField: ElementRef;

    ngOnInit() {
        this.form.controls.sprintId.setValue(this.root.sprintId);
        this.descriptionField.nativeElement.focus();
    }

    postUserStory(event) {
        event.preventDefault();
        this.userStoryService.postUserStory(this.form.value).subscribe(
            () => this.newUserStory.emit(),
            error => console.warn(error),
        );
    }

    closeUserStoryForm() {
        this.close.emit();
    }

    onKeyPress(event: KeyboardEvent) {
        if (event.keyCode === 13 && !event.shiftKey) {
            event.preventDefault();
            this.postUserStory(event);
        }
    }
}
