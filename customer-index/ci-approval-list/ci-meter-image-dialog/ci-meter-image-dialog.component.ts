import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-ci-meter-image-dialog',
  templateUrl: './ci-meter-image-dialog.component.html',
  styleUrls: ['./ci-meter-image-dialog.component.css']
})
export class CiMeterImageDialogComponent implements OnInit {


  constructor(
    public dialogRef: MatDialogRef<CiMeterImageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public photoBase64: string,) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
