import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';

@Component({
    selector: 'app-user-story',
    templateUrl: './user-story.component.html',
    styleUrls: ['./user-story.component.css'],
})
export class UserStoryComponent implements OnInit {
    @Output() deleteMe = new EventEmitter();
    @Input() title: string;
    @Input() description: string;
    @ViewChild('container', {static: true}) container: ElementRef;

    constructor() { }

    ngOnInit() {
    }

    emitDeleteRequest() {
        this.deleteMe.emit();
    }

    addGrabbingStyle() {
        this.container.nativeElement.className = 'container bg-light user-story-container grabbing';
    }

    removeGrabbingStyle() {
        this.container.nativeElement.className = 'container bg-light user-story-container';
    }
}
