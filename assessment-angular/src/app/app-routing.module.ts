import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AssessmentListComponent } from './components/assessment-list/assessment-list.component';
import { AssessmentDetailsComponent } from './components/assessment-details/assessment-details.component';
import { AddAssessmentComponent } from './components/add-assessment/add-assessment.component';
import {NgbPaginationModule, NgbAlertModule} from '@ng-bootstrap/ng-bootstrap';
import {AssessmentViewComponent} from './components/assessment-view/assessment-view.component';


const routes: Routes = [
  { path: '', redirectTo: 'assessments', pathMatch: 'full' },
  { path: 'assessments', component: AssessmentListComponent },
  { path: 'assessments/:id', component: AssessmentDetailsComponent },
  { path: 'assessments/job/:id', component: AssessmentViewComponent },
  { path: 'add', component: AddAssessmentComponent }
];

@NgModule({
  imports: [NgbPaginationModule, NgbAlertModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
