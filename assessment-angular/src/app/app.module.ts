import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAssessmentComponent } from './components/add-assessment/add-assessment.component';
import { AssessmentDetailsComponent } from './components/assessment-details/assessment-details.component';
import { AssessmentListComponent } from './components/assessment-list/assessment-list.component';



import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AssessmentViewComponent } from './components/assessment-view/assessment-view.component';

@NgModule({
  declarations: [
    AppComponent,
    AddAssessmentComponent,
    AssessmentDetailsComponent,
    AssessmentListComponent,
    AssessmentViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
