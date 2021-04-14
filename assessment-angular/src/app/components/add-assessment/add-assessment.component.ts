import { Component, OnInit } from '@angular/core';
import { AssessmentService } from 'src/app/services/assessment.service';

@Component({
  selector: 'app-add-assessment',
  templateUrl: './add-assessment.component.html',
  styleUrls: ['./add-assessment.component.css']
})
export class AddAssessmentComponent implements OnInit {

  assessment = {
    title: '',
    description: '',
    experience: '',
    status: 'new'
  };
  submitted = false;

  constructor(private assessmentService: AssessmentService) { }

  ngOnInit(): void {
  }

  saveAssessment(): void {
    const data = {
      title: this.assessment.title,
      description: this.assessment.description,
      experience: this.assessment.experience
    };

    this.assessmentService.create(data)
      .subscribe(
        response => {
          console.log(response);
          this.submitted = true;
        },
        error => {
          console.log(error);
        });
  }

  newAssessment(): void {
    this.submitted = false;
    this.assessment = {
      title: '',
      description: '',
      experience: '',
      status: 'new'
    };
  }


}
