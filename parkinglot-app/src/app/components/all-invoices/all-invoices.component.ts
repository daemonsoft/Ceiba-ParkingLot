import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource } from '@angular/material';
import { Invoice } from '../invoices/invoices.component';
import { InvoiceService } from '../../services/invoice.service';

@Component({
  selector: 'pl-all-invoices',
  templateUrl: './all-invoices.component.html',
  styleUrls: ['./all-invoices.component.scss']
})
export class AllInvoicesComponent implements OnInit {

  invoices = new MatTableDataSource<Invoice>(this.invoices);
  displayedColumns: string[] = ['id', 'vehicle', 'type', 'entryDate', 'exitDate', 'amount'];
  @ViewChild(MatSort) sort: MatSort;

  constructor(private service: InvoiceService) { }

  ngOnInit() {
    this.invoices.sort = this.sort;
    this.service.getAll()
      .subscribe(invoices => {
        this.invoices = invoices.json();
        
      });
  }
}
