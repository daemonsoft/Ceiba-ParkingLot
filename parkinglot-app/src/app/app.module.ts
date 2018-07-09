import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule, MatSnackBarContainer, MatSnackBarModule, MatSortModule } from '@angular/material'
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http'
import { AppComponent } from './app.component';
import { VehicleEntryComponent } from './components/vehicle-entry/vehicle-entry.component';
import { VehiclesComponent } from './components/vehicles/vehicles.component';
import { InvoiceService } from './services/invoice.service';
import { VehicleService } from './services/vehicle.service';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { MatTabComponent } from './components/mat-tab/mat-tab.component';
import { MatTabsModule } from '@angular/material';
import { InvoicesComponent } from './components/invoices/invoices.component';
import { HomeComponent } from './components/home/home.component';
import { MatTableModule } from '@angular/material/table';
import { MatSnackBar } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AllInvoicesComponent } from './components/all-invoices/all-invoices.component';
import { AppErrorHandler } from './common/app-error-handler';

const appRoutes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'invoices',
    component: MatTabComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
]

@NgModule({
  declarations: [
    AppComponent,
    VehicleEntryComponent,
    VehiclesComponent,
    HeaderComponent,
    PageNotFoundComponent,
    MatTabComponent,
    InvoicesComponent,
    HomeComponent,
    AllInvoicesComponent
  ],
  entryComponents: [
    MatSnackBarContainer
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    BrowserAnimationsModule, // new modules added here
    MatToolbarModule,
    MatCardModule,
    HttpModule,
    MatTabsModule,
    MatTableModule,
    MatSnackBarModule,
    FlexLayoutModule,
    RouterModule.forRoot(
      appRoutes
      // ,      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [
    InvoiceService,
    VehicleService,
    MatSnackBar,
    { provide: ErrorHandler, useClass: AppErrorHandler }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
