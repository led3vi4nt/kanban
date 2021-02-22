import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BoardViewComponent } from './components/views/board-view/board-view.component';
import { LoginViewComponent } from './components/views/login-view/login-view.component';
import { SprintEditViewComponent } from './components/views/sprint-edit-view/sprint-edit-view.component';
import { SprintViewComponent } from './components/views/sprint-view/sprint-view.component';


const routes: Routes = [
    {path: '', component: SprintViewComponent},
    {path: 'sprints/:id', component: BoardViewComponent},
    {path: 'edit/:id', component: SprintEditViewComponent},
    {path: 'login', component: LoginViewComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {
}
