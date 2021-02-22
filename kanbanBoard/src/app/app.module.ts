import { DragDropModule } from '@angular/cdk/drag-drop';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SprintViewComponent } from './components/views/sprint-view/sprint-view.component';
import { BoardViewComponent } from './components/views/board-view/board-view.component';
import { SprintFormComponent } from './components/units/sprint-form/sprint-form.component';
import { UserStoryFormComponent } from './components/units/user-story-form/user-story-form.component';
import { SprintListComponent } from './components/units/sprint-list/sprint-list.component';
import { UserStoryComponent } from './components/units/user-story/user-story.component';
import { SprintEditViewComponent } from './components/views/sprint-edit-view/sprint-edit-view.component';
import { HttpRequestInterceptor } from './utils/httpRequestInterceptor';
import { LoginViewComponent } from './components/views/login-view/login-view.component';

@NgModule({
    declarations: [
        AppComponent,
        SprintViewComponent,
        BoardViewComponent,
        SprintFormComponent,
        UserStoryFormComponent,
        SprintListComponent,
        UserStoryComponent,
        SprintEditViewComponent,
        LoginViewComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule,
        DragDropModule,
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpRequestInterceptor,
            multi: true,
        },
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
