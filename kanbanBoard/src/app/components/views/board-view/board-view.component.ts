import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserStoryDetails } from '../../../models/user-story-details.model';
import { RootService } from '../../../services/root.service';
import { UserStoryService } from '../../../services/user-story.service';

@Component({
    selector: 'app-board-view',
    templateUrl: './board-view.component.html',
    styleUrls: ['./board-view.component.css'],
})
export class BoardViewComponent implements OnInit, OnDestroy {
    userStories: Array<Array<UserStoryDetails>>;
    tracks = {
        todo: 'To Do',
        inProgress: 'In Progress',
        testing: 'Testing',
        done: 'Done',
    };
    trackIds = Object.keys(this.tracks);
    titles = [];
    trackStyles = ['bg-danger', 'bg-warning', 'bg-info', 'bg-success'];
    showStoryForm: boolean = false;
    nextStoryId: number;

    constructor(private root: RootService,
                private router: Router,
                private route: ActivatedRoute,
                private userStoryService: UserStoryService) {
        for (let title in this.tracks) {
            this.titles.push(this.tracks[title]);
        }
    }

    ngOnInit() {
        if (!localStorage.getItem('user')) {
            this.router.navigate(['/login']);
        }
        this.route.paramMap.subscribe(
            paramMap => {
                this.root.sprintId = +paramMap.get('id');
                if (this.root.sprintId) {
                    this.loadUserStories();
                }
            },
            error => console.warn(error),
        );
    }

    ngOnDestroy(): void {
        this.root.callerUrl = 'sprints/' + this.root.sprintId;
    }

    loadUserStories() {
        this.showStoryForm = false;
        this.userStories = [[], [], [], []];
        this.userStoryService.getAllUserStoriesOfSprint(this.root.sprintId).subscribe(
            userStories => this.processUserStories(userStories),
            error => console.warn(error),
        );
    }

    deleteUserStory(id: number) {
        this.userStoryService.deleteUserStory(id).subscribe(
            () => {this.removeFromUserStories(id);},
            error => console.warn(error),
        );
    }

    private removeFromUserStories(id: number) {
        for (let track of this.userStories) {
            for (let i = 0; i < track.length; i++) {
                if (track[i].id === id) {
                    track.splice(i, 1);
                    return;
                }
            }
        }
    }

    drop(event: CdkDragDrop<Array<UserStoryDetails>>) {
        let userStoryId = event.item.element.nativeElement.id;
        let userStoryState = this.tracks[event.container.id];
        this.userStoryService.updateStateOfUserStory({userStoryId: +userStoryId, userStoryState: userStoryState}).subscribe();
        if (event.previousContainer === event.container) {
            moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
        } else {
            transferArrayItem(event.previousContainer.data,
                event.container.data,
                event.previousIndex,
                event.currentIndex);
        }
    }

    processUserStories(userStories: Array<UserStoryDetails>) {
        this.nextStoryId = userStories.length + 1;
        for (let userStory of userStories) {
            switch (userStory.state) {
                case 'To Do':
                    this.userStories[0].push(userStory);
                    break;
                case 'In Progress':
                    this.userStories[1].push(userStory);
                    break;
                case 'Testing':
                    this.userStories[2].push(userStory);
                    break;
                case 'Done':
                    this.userStories[3].push(userStory);
                    break;
                default:
                    this.userStories[0].push(userStory);
            }
        }
    }

    textDrop(event: DragEvent) {
        event.preventDefault();
        event.stopPropagation();
        let text = event.dataTransfer.getData('text');
        let data = {
            description: text,
            sprintId: this.root.sprintId,
        };
        this.userStoryService.postUserStory(data).subscribe(
            () => this.loadUserStories(),
            error => console.warn(error),
        );
    }

    switchUserStoryForm(to: boolean) {
        this.showStoryForm = to;
    }

    navigateBackToSprintView() {
        this.router.navigate(['']);
    }

    @HostListener('window:keyup', ['$event'])
    keyEvent(event: KeyboardEvent) {
        if (!this.showStoryForm && event.key == 'a') {
            this.switchUserStoryForm(true);
        }
    }

    navigateToSprintEditView() {
        this.router.navigate(['/edit', this.root.sprintId]);
    }
}
