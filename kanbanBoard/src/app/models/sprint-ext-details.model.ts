import { UserStoryDetails } from './user-story-details.model';

export class SprintExtDetails {
    id: number;
    badge: string;
    userStories: Array<UserStoryDetails>;
    description: string;
    sprintState: string;
    createdAt: string;
    doneAt: string;
}
