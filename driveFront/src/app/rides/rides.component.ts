import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Rating, RidePreview} from "../model/ride-preview";
import {RideService} from "../service/ride.service";
import {RideLocationComponent} from "../ride-location/ride-location.component";
import {MatDialog} from "@angular/material/dialog";
import {RatingComponent} from "../rating/rating.component";
import {coerceNumberProperty} from "@angular/cdk/coercion";

@Component({
  selector: 'app-rides',
  templateUrl: './rides.component.html',
  styleUrls: ['./rides.component.css']
})
export class RidesComponent implements  OnInit{
  rides:RidePreview[] = []

  constructor(private service: RideService,
              private route: ActivatedRoute,
              private router: Router,
              public dialog: MatDialog) {

  }

  rate(id: number) {
    const dialogRef = this.dialog.open(RatingComponent);

    dialogRef.afterClosed().subscribe(
      {next: (data:Rating) => {
        this.service.rate(id, data).subscribe(
          {
            next: () => {
              this.rides.forEach(el => {
                if(el.id == id){
                  el.rating = data
                }
              })
            }
          }
        )
        }}
    );
  }

  ngOnInit(): void {
    this.service.getRides().subscribe({
      next: (r) => {
        this.rides = r
        console.log(this.rides)
      }
    });
  }
}

//simulation se kreira kada se naoravi ride
//simulation vehicle, steps,
//steps se brisu kad prodje
//kad se isprazni voznje finicshe, driver free, simlation remove


//ako auto ide 60km/h izracunam koliko treba km da pradje za 5s
//uzimam koordinate redom x y izracunam razdaljinu
  //ako je razdaljina manja dodam sl tacku
    //na kraju ga prebacim na novu kooridnu
