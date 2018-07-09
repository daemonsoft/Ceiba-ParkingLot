import { Component, OnInit, ViewChild } from '@angular/core';
import { InvoiceService } from '../../services/invoice.service';
import { Vehicle } from '../vehicles/vehicles.component';
import { MatPaginator, MatTableDataSource } from '@angular/material';

export interface Invoice {
  id: number;
  vehicle: Vehicle;
  entryDate: Date;
  exitDate: Date;
}

@Component({
  selector: 'pl-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss']
})
export class InvoicesComponent implements OnInit {
  invoices = new MatTableDataSource<Invoice>(this.invoices);
  displayedColumns: string[] = ['id', 'vehicle', 'type', 'entryDate', 'exitDate'];
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private service: InvoiceService) { }

  ngOnInit() {
    this.service.getAll()
      .subscribe(invoices => {
        this.invoices = invoices.json();
        this.invoices.paginator = this.paginator;
      });
  }
}
